package com.example.rcm.strategy;

import com.example.rcm.service.IFareStrategy;
import org.springframework.stereotype.Service;

@Service
public class BusinessPricing implements IFareStrategy {
    @Override
    public String calculateFare(String typeOfSeat) {
        return "2000";
    }
}
