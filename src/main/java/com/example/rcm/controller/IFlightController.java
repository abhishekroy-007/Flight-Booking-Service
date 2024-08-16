package com.example.rcm.controller;

import com.example.rcm.entity.Flight;
import com.example.rcm.model.FlightCancelRequest;
import com.example.rcm.model.FlightRequest;
import com.example.rcm.model.FlightResponse;
import com.example.rcm.model.FlightUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface IFlightController {

    @PostMapping(path = "/v1/createFlight")
    ResponseEntity<FlightResponse> createFlight(@RequestBody FlightRequest flightRequest);

    @GetMapping(path = "/v1/getFlightByOriginAndDestinationAndTimeOfFly")
    ResponseEntity<List<FlightResponse>> getFlightByOriginAndDestinationAndDataOfFly
            (@RequestParam String origin, @RequestParam String destination, @RequestParam String timeOfFly,
             @RequestParam String endTimeOfFly);

    @PostMapping(path = "/v1/updateFlight")
    ResponseEntity<FlightResponse> updateFlight(@RequestBody FlightUpdateRequest flightUpdateRequest);

    @PostMapping(path = "/v1/cancelFlight")
    ResponseEntity<FlightResponse> cancelFlight(@RequestBody FlightCancelRequest flightCancelRequest);

    @GetMapping(path = "/v1/getAllFlights")
    ResponseEntity<List<Flight>> getAllFlights();
}
