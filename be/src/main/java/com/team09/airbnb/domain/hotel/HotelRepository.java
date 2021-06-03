package com.team09.airbnb.domain.hotel;

import com.team09.airbnb.request.HotelRequest;
import com.team09.airbnb.response.HotelResponse;
import com.team09.airbnb.util.LocationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class HotelRepository {

    private final Logger logger = LoggerFactory.getLogger(HotelRepository.class);

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public HotelRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<HotelResponse> findAll(HotelRequest hotelRequest) {

        final String SEARCH_HOTEL_SQL =
                "SELECT h.hotel_id, h.title, h.price, IF(w.user_name IS NULL, 'false', 'true') as wishlist, ST_X(h.location) as latitude, ST_Y(h.location) as longitude, h.rate, h.main_img, o.`option`" +
                        "FROM hotel h " +
                        "LEFT JOIN wishlist w ON h.hotel_id = w.hotel_id " +
                        "LEFT JOIN `options` o ON h.hotel_id = o.id " +
                        "LEFT JOIN reservation r on h.hotel_id = r.hotel_id " +
                        "WHERE ST_CONTAINS(ST_LINESTRINGFROMTEXT(CONCAT('LINESTRING(',:sw_lat,' ',:sw_lng,',',:ne_lat,' ',:ne_lng,')')), h.location) = 0 " +
                        "AND h.hotel_id NOT IN (" +
                        "   SELECT r.hotel_id FROM reservation r " +
                        "   WHERE " +
                        "      r.checkin <= :checkin AND r.checkout > :checkin " +
                        "   OR r.checkin < :checkout AND r.checkout >= :checkout " +
                        "   OR :checkin <= r.checkin  AND :checkout > r.checkin" +
                        ")" +
                        "AND h.price BETWEEN :min_price AND :max_price " +
                        "AND h.occupancy >= :occupancy" +
                        ";";
        Map<String, Double> area = LocationUtil.getArea(hotelRequest.getLatitude(), hotelRequest.getLongitude());

        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("sw_lat", area.get("sw_lat"))
                .addValue("sw_lng", area.get("sw_lng"))
                .addValue("ne_lat", area.get("ne_lat"))
                .addValue("ne_lng", area.get("ne_lng"))
                .addValue("checkin", hotelRequest.getCheckin())
                .addValue("checkout", hotelRequest.getCheckout())
                .addValue("min_price", hotelRequest.getPricemin())
                .addValue("max_price", hotelRequest.getPricemax())
                .addValue("occupancy", hotelRequest.getOccupancy());

        logger.info("south west latitude : {} ", area.get("sw_lat"));
        logger.info("south west longitude : {} ", area.get("sw_lng"));
        logger.info("north east latitude : {} ", area.get("ne_lat"));
        logger.info("north east longitude : {} ", area.get("ne_lng"));
        logger.info("check in : {} ", hotelRequest.getCheckin());
        logger.info("check out : {} ", hotelRequest.getCheckout());

        return jdbcTemplate.query(SEARCH_HOTEL_SQL, parameterSource, rs -> {
            Map<Long, HotelResponse> resultMap = new LinkedHashMap<>();
            while (rs.next()) {
                Long id = rs.getLong("hotel_id");
                if (!resultMap.containsKey(id)) {
                    resultMap.put(id, HotelResponse
                            .of(rs.getLong("hotel_id"))
                            .latitude(rs.getDouble("latitude"))
                            .longitude(rs.getDouble("longitude"))
                            .title(rs.getString("title"))
                            .price(rs.getBigDecimal("price"))
                            .wishlist(rs.getBoolean("wishlist"))
                            .rate(rs.getInt("rate"))
                            .img(rs.getString("main_img"))
                            .build());
                }
                resultMap.get(id).addOption(rs.getString("option"));
            }
            return new ArrayList<HotelResponse>(resultMap.values());
        });
    }


}
