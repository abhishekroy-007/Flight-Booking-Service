package com.example.rcm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface IFareController {

    @GetMapping(path = "/v1/getFareOfBooking")
    ResponseEntity<String> getFareOfBooking(@RequestParam String flightId, @RequestParam String typeOfSeat);

}
