package com.example.rcm.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightRequest {
    @JsonProperty("airline_id")
    @Column(name = "airline_id")
    private String airlineId;

    @JsonProperty("origin")
    @Column(name = "origin")
    private String origin;

    @JsonProperty("destination")
    @Column(name = "destination")
    private String destination;

    @JsonProperty("start_time")
    @Column(name = "start_time")
    private String startTime;

    @JsonProperty("end_time")
    @Column(name = "end_time")
    private String endTime;

    @JsonProperty("total_premium_seats")
    @Column(name = "total_premium_seats")
    private String totalPremiumSeats;

    @JsonProperty("total_economy_seats")
    @Column(name = "total_economy_seats")
    private String totalEconomySeats;

    @JsonProperty("total_business_seats")
    @Column(name = "total_business_seats")
    private String totalBusinessSeats;

    @JsonProperty("base_fare")
    @Column(name = "base_fare")
    private String baseFare;
}
