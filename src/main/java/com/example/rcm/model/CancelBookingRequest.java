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
public class CancelBookingRequest {
    @JsonProperty("booking_id")
    @Column(name = "booking_id")
    private String bookingId;
}
