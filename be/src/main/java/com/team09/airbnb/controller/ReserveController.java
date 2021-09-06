package com.team09.airbnb.controller;

import com.team09.airbnb.request.ReserveRequest;
import com.team09.airbnb.response.ApiResponse;
import com.team09.airbnb.response.ReservedDetailResponse;
import com.team09.airbnb.response.ReservedResponse;
import com.team09.airbnb.service.HotelService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reserve")
public class ReserveController {

    private final HotelService hotelService;

    public ReserveController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public List<ReservedResponse> reservedList() {
        ReservedResponse response1 = new ReservedResponse(1L, LocalDate.of(2021, 06, 01), LocalDate.of(2021, 06, 10), 2, 0, 0, "C1 New apartment right next to Gangnam Station");
        ReservedResponse response2 = new ReservedResponse(1L, LocalDate.of(2021, 06, 11), LocalDate.of(2021, 06, 15), 2, 1, 1, "Specious Central Itaewon – NEWTRO House");

        return Arrays.asList(response1, response2);
    }

    @GetMapping("/{reservedId}")
    public ReservedDetailResponse reservedDetail(@PathVariable Long reservedId) {
        return ReservedDetailResponse.create(reservedId, "C1 New apartment right next to Gangnam Station", BigDecimal.valueOf(79000), false, 37.498063, 127.030187, 4, Arrays.asList("주방", "무선인터넷", "에어컨", "헤어드라이"), "Baba",
                Arrays.asList("https://team09-airbnb.s3.ap-northeast-2.amazonaws.com/airbnb1-1.png", "https://team09-airbnb.s3.ap-northeast-2.amazonaws.com/airbnb1-2.png", "https://team09-airbnb.s3.ap-northeast-2.amazonaws.com/airbnb1-3.png"),1L, LocalDate.of(2021, 06, 01), LocalDate.of(2021, 06, 11), 2, 0, 0);
    }

    @PostMapping
    public ApiResponse reserve(@RequestBody ReserveRequest reserveRequest) {
        return new ApiResponse(hotelService.reserveHotel(reserveRequest));
    }
}
