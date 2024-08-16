package com.example.rcm.controller;

import com.example.rcm.service.FareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FareController implements IFareController {

    @Autowired
    FareService fareService;

    @Override
    public ResponseEntity<String> getFareOfBooking(@RequestParam String flightId, @RequestParam String typeOfSeat) {
        String fareAmount = fareService.getFareOfBooking(flightId, typeOfSeat);
        return ResponseEntity.status(HttpStatus.OK).body(fareAmount);
    }
}
