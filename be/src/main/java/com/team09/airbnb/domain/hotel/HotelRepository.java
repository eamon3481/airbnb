package com.team09.airbnb.domain.hotel;

import com.team09.airbnb.request.HotelRequest;
import com.team09.airbnb.request.PriceRequest;
import com.team09.airbnb.request.ReserveRequest;
import com.team09.airbnb.response.HotelDetailResponse;
import com.team09.airbnb.response.HotelResponse;
import com.team09.airbnb.response.ReserveResponse;
import com.team09.airbnb.util.LocationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
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
                        "LEFT JOIN reservation r on h.hotel_id = r.hotel_id " +
                        "LEFT JOIN wishlist w ON w.user_name = :username " +
                        "LEFT JOIN `options` o ON h.hotel_id = o.hotel_id " +
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
                .addValue("occupancy", hotelRequest.getOccupancy())
                .addValue("username", "jihye-woo");

        logger.info("south west latitude : {} ", area.get("sw_lat"));
        logger.info("south west longitude : {} ", area.get("sw_lng"));
        logger.info("north east latitude : {} ", area.get("ne_lat"));
        logger.info("north east longitude : {} ", area.get("ne_lng"));
        logger.info("check in : {} ", hotelRequest.getCheckin());
        logger.info("check out : {} ", hotelRequest.getCheckout());

        return jdbcTemplate.query(SEARCH_HOTEL_SQL, parameterSource, rs -> {
            Map<Long, HotelResponse> hotelResponseMap = new HashMap<>();

            while (rs.next()) {
                Long hotelId = rs.getLong("hotel_id");
                if (!hotelResponseMap.containsKey(hotelId)) {
                    HotelResponse hotelResponse = HotelResponse
                            .of(hotelId)
                            .latitude(rs.getDouble("latitude"))
                            .longitude(rs.getDouble("longitude"))
                            .title(rs.getString("title"))
                            .price(rs.getBigDecimal("price"))
                            .wishlist(rs.getBoolean("wishlist"))
                            .rate(rs.getInt("rate"))
                            .img(rs.getString("main_img"))
                            .build();
                    hotelResponseMap.put(hotelId, hotelResponse);
                }
                HotelResponse response = hotelResponseMap.get(hotelId);
                response.addOption(rs.getString("option"));
                hotelResponseMap.put(hotelId, response);
            }
            return new ArrayList<HotelResponse>(hotelResponseMap.values());
        });
    }

    public List<BigDecimal> prices(PriceRequest priceRequest) {
        String sql = "SELECT h.price " +
                "FROM hotel h " +
                "LEFT JOIN reservation r on h.hotel_id = r.hotel_id " +
                "WHERE ST_CONTAINS(ST_LINESTRINGFROMTEXT(CONCAT('LINESTRING(',:sw_lat,' ',:sw_lng,',',:ne_lat,' ',:ne_lng,')')), h.location) = 0 " +
                "AND h.hotel_id NOT IN (" +
                "   SELECT r.hotel_id FROM reservation r " +
                "   WHERE " +
                "      r.checkin <= :checkin AND r.checkout > :checkin " +
                "   OR r.checkin < :checkout AND r.checkout >= :checkout " +
                "   OR :checkin <= r.checkin  AND :checkout > r.checkin" +
                ");";

        Map<String, Double> area = LocationUtil.getArea(priceRequest.getLatitude(), priceRequest.getLongitude());

        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("sw_lat", area.get("sw_lat"))
                .addValue("sw_lng", area.get("sw_lng"))
                .addValue("ne_lat", area.get("ne_lat"))
                .addValue("ne_lng", area.get("ne_lng"))

                .addValue("checkin", priceRequest.getCheckin())
                .addValue("checkout", priceRequest.getCheckout());

        return jdbcTemplate.query(sql, parameterSource, (rs, rowNum) -> rs.getBigDecimal("price"));
    }

    public HotelDetailResponse hotelDetail(Long hotelId, String username) {

        final String SELECT_HOTEL_DETAIL =
                "SELECT h.hotel_id, h.title, h.price, o.`option`, ST_X(h.location) as latitude, ST_Y(h.location) as longitude, h.rate, " +
                        "IF(w.user_name IS NULL, 'false', 'true') as wishlist " +
                        "FROM hotel h " +
                        "INNER JOIN `options`o ON h.hotel_id = o.hotel_id " +
                        "LEFT JOIN wishlist w ON w.user_name = :username " +
                        "WHERE h.hotel_id = :hotel_id  GROUP BY o.`option`;";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("hotel_id", hotelId)
                .addValue("username", username);

        List<String> detailImgs = hotelDetailImgs(hotelId);

        return jdbcTemplate.query(SELECT_HOTEL_DETAIL, parameterSource, rs -> {
            HotelDetailResponse hotelDetail = null;
            while (rs.next()) {
                if (hotelDetail == null) {
                    hotelDetail = HotelDetailResponse.of(rs.getLong("hotel_id"))
                            .latitude(rs.getDouble("latitude"))
                            .longitude(rs.getDouble("longitude"))
                            .title(rs.getString("title"))
                            .price(rs.getBigDecimal("price"))
                            .wishlist(rs.getBoolean("wishlist"))
                            .rate(rs.getInt("rate"))
                            .build();
                    hotelDetail.setImgs(detailImgs);
                }
                hotelDetail.addOption(rs.getString("option"));
            }
            return hotelDetail;
        });
    }

    public List<String> hotelDetailImgs(Long hotelId) {

        final String SELECT_HOTEL_DETAIL_IMGS = "" +
                "SELECT image_url AS img " +
                "FROM `detail_image` " +
                "WHERE hotel_id = :hotel_id;";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("hotel_id", hotelId);

        return jdbcTemplate.query(SELECT_HOTEL_DETAIL_IMGS, parameterSource, (rs, rowNum) -> rs.getString("img"));
    }


    public ReserveResponse reserveHotel(ReserveRequest reserveRequest) {
        final String INSERT_RESERVATION = "INSERT INTO `reservation` (hotel_id, checkin, checkout, price, total_price, adults, children, infants, user_name) " +
                "SELECT :hotelId, :checkin, :checkout, price, price * :days, :adults, :children, :infants, :username FROM hotel WHERE hotel_id = :hotelId; ";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        logger.info("hotelId : {} ", reserveRequest.getHotelId());
        logger.info("check in : {} ", reserveRequest.getCheckin());
        logger.info("check out : {} ", reserveRequest.getCheckout());
        logger.info("username : {} ", reserveRequest.getUsername());

        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("hotelId", reserveRequest.getHotelId())
                .addValue("checkin", reserveRequest.getCheckin())
                .addValue("checkout", reserveRequest.getCheckout())
                .addValue("days", reserveRequest.getDays())
                .addValue("adults", reserveRequest.getAdults())
                .addValue("children", reserveRequest.getChildren())
                .addValue("infants", reserveRequest.getInfants())
                .addValue("username", reserveRequest.getUsername());

        jdbcTemplate.update(INSERT_RESERVATION, parameterSource, keyHolder);
        return new ReserveResponse(keyHolder.getKey().longValue());
    }
}
