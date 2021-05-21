package com.team09.airbnb.controller;

import com.team09.airbnb.request.ReserveRequest;
import com.team09.airbnb.response.ReservedDetailResponse;
import com.team09.airbnb.response.ReservedResponse;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reserve")
public class ReserveController {

    @GetMapping
    public List<ReservedResponse> reservedList() {
        ReservedResponse response1 = new ReservedResponse(1L, new Date(2021, 06, 01), new Date(2021, 06, 10), 2, 0, 0, "C1 New apartment right next to Gangnam Station");
        ReservedResponse response2 = new ReservedResponse(1L, new Date(2021, 06, 11), new Date(2021, 06, 15), 2, 1, 1, "Specious Central Itaewon – NEWTRO House");

        return Arrays.asList(response1, response2);
    }

    @GetMapping("/{reservedId}")
    public ReservedDetailResponse reservedDetail(@PathVariable Long reservedId) {
        return new ReservedDetailResponse(1L, "C1 New apartment right next to Gangnam Station", BigDecimal.valueOf(79000), false, 37.498063, 127.030187, 4, Arrays.asList("주방", "무선인터넷", "에어컨", "헤어드라이"), "Baba", Arrays.asList("https://team09-airbnb.s3.ap-northeast-2.amazonaws.com/airbnb1-1.png", "https://team09-airbnb.s3.ap-northeast-2.amazonaws.com/airbnb1-2.png", "https://team09-airbnb.s3.ap-northeast-2.amazonaws.com/airbnb1-3.png"),
                1L, new Date(2021, 06, 01), new Date(2021, 06, 10), 2, 0, 0);
    }

}
