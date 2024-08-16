package com.example.rcm.strategy;

import com.example.rcm.model.TypeOfSeat;
import com.example.rcm.service.IFareStrategy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class EconomyPricing implements IFareStrategy {

    @Override
    public String calculateFare(String typeOfSeat) {
        return "1000";
    }
}
