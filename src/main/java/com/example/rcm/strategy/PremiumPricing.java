package com.example.rcm.strategy;

import com.example.rcm.service.IFareStrategy;
import org.springframework.stereotype.Service;

@Service
public class PremiumPricing implements IFareStrategy {
    @Override
    public String calculateFare(String typeOfSeat) {
        return "3000";
    }
}
