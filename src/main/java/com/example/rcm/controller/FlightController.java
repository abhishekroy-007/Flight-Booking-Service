package com.example.rcm.controller;

import com.example.rcm.entity.Flight;
import com.example.rcm.model.FlightCancelRequest;
import com.example.rcm.model.FlightRequest;
import com.example.rcm.model.FlightResponse;
import com.example.rcm.model.FlightUpdateRequest;
import com.example.rcm.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlightController implements IFlightController {

    @Autowired
    FlightService flightService;

    @Override
    public ResponseEntity<FlightResponse> createFlight(@RequestBody FlightRequest flightRequest) {
        FlightResponse flightResponse = flightService.createFlight(flightRequest);
        return ResponseEntity.status(HttpStatus.OK).body(flightResponse);
    }

    @Override
    public ResponseEntity<List<FlightResponse>> getFlightByOriginAndDestinationAndDataOfFly
            (@RequestParam String origin, @RequestParam String destination, @RequestParam String timeOfFly,
             @RequestParam String endTimeOfFly) {
        List<FlightResponse> flightResponse = flightService.getAllFlightByOriginAndDestinationAndDataOfFly
                (origin, destination, timeOfFly, endTimeOfFly);
        if (flightResponse == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(flightResponse);
    }

    @Override
    public ResponseEntity<FlightResponse> updateFlight(@RequestBody FlightUpdateRequest flightUpdateRequest) {
        FlightResponse flightResponse = flightService.updateFlight(flightUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(flightResponse);
    }

    @Override
    public ResponseEntity<FlightResponse> cancelFlight(@RequestBody FlightCancelRequest flightCancelRequest) {
        FlightResponse flightResponse = flightService.cancelFlight(flightCancelRequest);
        return ResponseEntity.status(HttpStatus.OK).body(flightResponse);
    }

    @Override
    public ResponseEntity<List<Flight>> getAllFlights() {
        List<Flight> flightResponse = flightService.getAllFlights();
        return ResponseEntity.status(HttpStatus.OK).body(flightResponse);
    }

}
