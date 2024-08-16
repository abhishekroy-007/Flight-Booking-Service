package com.example.rcm.controller;

import com.example.rcm.entity.Booking;
import com.example.rcm.model.BookingRequest;
import com.example.rcm.model.BookingResponse;
import com.example.rcm.model.CancelBookingRequest;
import com.example.rcm.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookingController implements IBookingController {
    @Autowired
    BookingService bookingService;

    @Override
    public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest bookingRequest) {
        BookingResponse booking = bookingService.createBooking(bookingRequest);
        return ResponseEntity.status(HttpStatus.OK).body(booking);
    }

    @Override
    public ResponseEntity<Booking> cancelBooking(@RequestBody CancelBookingRequest cancelBookingRequest) {
        Booking booking = bookingService.cancelBooking(cancelBookingRequest);
        return ResponseEntity.status(HttpStatus.OK).body(booking);
    }

    @Override
    public ResponseEntity<List<Booking>> getAllBooking() {
        List<Booking> bookings = bookingService.getAllBooking();
        return ResponseEntity.status(HttpStatus.OK).body(bookings);
    }
}
