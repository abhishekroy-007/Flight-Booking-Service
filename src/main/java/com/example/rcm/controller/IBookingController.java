package com.example.rcm.controller;

import com.example.rcm.entity.Booking;
import com.example.rcm.model.BookingRequest;
import com.example.rcm.model.BookingResponse;
import com.example.rcm.model.CancelBookingRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IBookingController {
    @PostMapping(path = "/v1/createBooking")
    ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest bookingRequest);

    @PostMapping(path = "/v1/cancelBooking")
    ResponseEntity<Booking> cancelBooking(@RequestBody CancelBookingRequest bookingRequest);

    @GetMapping(path = "/v1/getAllBooking")
    ResponseEntity<List<Booking>> getAllBooking();
}
