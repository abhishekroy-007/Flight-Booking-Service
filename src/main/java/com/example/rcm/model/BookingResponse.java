package com.example.rcm.model;

import com.example.rcm.entity.Booking;
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
public class BookingResponse {
    @JsonProperty("booked")
    @Column(name = "booked")
    Booking booking;
}
