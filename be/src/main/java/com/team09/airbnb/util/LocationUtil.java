package com.team09.airbnb.util;

import java.util.HashMap;
import java.util.Map;

public class LocationUtil {

    // 1 degree of latitude = approx 111 km
    private static final int UNIT = 111;
    private static final int DISTANCE = 10;

    private LocationUtil() {
    }

    public static Map<String, Double> getArea(double latitude, double longitude) {
        double LAT_CONVERTOR = DISTANCE / UNIT;
        double LNG_CONVERTOR = DISTANCE / Math.abs(Math.cos((Math.toRadians(latitude))));

        return new HashMap<String, Double>() {{
            put("sw_lat", latitude - LAT_CONVERTOR);
            put("sw_lng", longitude - LNG_CONVERTOR);
            put("ne_lat", latitude + LAT_CONVERTOR);
            put("ne_lng", longitude - LNG_CONVERTOR);
        }};
    }
}
