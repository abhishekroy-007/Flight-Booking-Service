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
public class FlightCancelRequest {
    @JsonProperty("flight_id")
    @Column(name = "flight_id")
    private String flightId;
}
