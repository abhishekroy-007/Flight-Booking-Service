package com.example.rcm.model;

import com.example.rcm.entity.Capacity;
import com.example.rcm.entity.Flight;
import com.example.rcm.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightResponse {
    Flight flight;
    Schedule schedule;
    Capacity capacity;
}
