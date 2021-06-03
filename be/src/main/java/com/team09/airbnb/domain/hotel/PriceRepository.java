package com.team09.airbnb.domain.hotel;

import com.team09.airbnb.request.PriceRequest;
import com.team09.airbnb.util.LocationUtil;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public class PriceRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public PriceRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
}
