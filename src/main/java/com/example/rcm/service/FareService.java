package com.example.rcm.service;

import com.example.rcm.model.TypeOfSeat;
import com.example.rcm.strategy.BusinessPricing;
import com.example.rcm.strategy.PremiumPricing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FareService {

    @Autowired
    IFareStrategy fareStrategy;

    @Autowired
    FlightService flightService;

    public String getFareOfBooking(String flightId, String typeOfSeat) {
        if (typeOfSeat.equalsIgnoreCase(String.valueOf(TypeOfSeat.PREMIUM))) {
            fareStrategy = new PremiumPricing();
        } else if (typeOfSeat.equalsIgnoreCase(String.valueOf(TypeOfSeat.BUSINESS))) {
            fareStrategy = new BusinessPricing();
        }
        String extraFare = fareStrategy.calculateFare(typeOfSeat);
        return String.valueOf(Integer.parseInt(extraFare) + Integer.parseInt(flightService.getBaseFareByFlightId(flightId)));
    }
}
