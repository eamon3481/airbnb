package com.team09.airbnb.controller;

import com.team09.airbnb.response.ReservedResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reserve")
public class ReserveController {

    @GetMapping
    public List<ReservedResponse> reservedList() {
        ReservedResponse response1 = new ReservedResponse(1L, "C1 New apartment right next to Gangnam Station", new Date(2021, 06, 01), new Date(2021, 06, 10), 2, 0, 0);
        ReservedResponse response2 = new ReservedResponse(1L, "Specious Central Itaewon – NEWTRO House", new Date(2021, 06, 11), new Date(2021, 06, 15), 2, 1, 1);

        return Arrays.asList(response1, response2);
    }


}
