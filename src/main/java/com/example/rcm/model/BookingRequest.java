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
public class BookingRequest {
    @JsonProperty("user_id")
    @Column(name = "user_id")
    private String userId;

    @JsonProperty("flight_id")
    @Column(name = "flight_id")
    private String flightId;

    @JsonProperty("seat_type")
    @Column(name = "seat_type")
    private String seatType;

    @JsonProperty("seat_count")
    @Column(name = "seat_count")
    private String seatCount;
}
