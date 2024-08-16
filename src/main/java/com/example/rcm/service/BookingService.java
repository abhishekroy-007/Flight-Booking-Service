package com.example.rcm.service;

import com.example.rcm.entity.Booking;
import com.example.rcm.entity.Capacity;
import com.example.rcm.entity.Flight;
import com.example.rcm.exception.BookingNotAvailableException;
import com.example.rcm.exception.FlightCancelledException;
import com.example.rcm.model.BookingRequest;
import com.example.rcm.model.BookingResponse;
import com.example.rcm.model.BookingStatus;
import com.example.rcm.model.CancelBookingRequest;
import com.example.rcm.model.FlightStatus;
import com.example.rcm.model.TypeOfSeat;
import com.example.rcm.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    FlightService flightService;

    @Autowired
    CapacityService capacityService;

    @Autowired
    FareService fareService;

    public BookingResponse createBooking(BookingRequest bookingRequest) {
        Booking booking = checkSeatsAvailableForBookingAndMakeBooking(bookingRequest);
        BookingResponse bookingResponse = BookingResponse.builder()
                .booking(booking)
                .build();
        return bookingResponse;
    }

    private Booking checkSeatsAvailableForBookingAndMakeBooking(BookingRequest bookingRequest) {
        // Check Seats Available For Booking
        Flight flight = flightService.getFlightById(bookingRequest.getFlightId());
        if(flight.getStatus().equalsIgnoreCase(FlightStatus.CANCELLED.toString())){
            throw new FlightCancelledException("Flight Has Been Cancelled , Book Different Flight");
        }
        Capacity capacity = capacityService.getByFlightId(flight.getId());
        String seatIds = "";
        if (bookingRequest.getSeatType().equalsIgnoreCase(String.valueOf(TypeOfSeat.PREMIUM))
                && Integer.parseInt(capacity.getAvailablePremiumSeats()) - Integer.parseInt(bookingRequest.getSeatCount()) >= 0) {
            seatIds = assignSeats(seatIds, capacity.getAvailablePremiumSeats(), Integer.parseInt(bookingRequest.getSeatCount()));
            capacity.setAvailablePremiumSeats(String.valueOf(Integer.parseInt(capacity.getAvailablePremiumSeats()) - Integer.parseInt(bookingRequest.getSeatCount())));
        } else if (bookingRequest.getSeatType().equalsIgnoreCase(String.valueOf(TypeOfSeat.BUSINESS))
                && Integer.parseInt(capacity.getAvailableBusinessSeats()) - Integer.parseInt(bookingRequest.getSeatCount()) >= 0) {
            seatIds = assignSeats(seatIds, capacity.getAvailableBusinessSeats(), Integer.parseInt(bookingRequest.getSeatCount()));
            capacity.setAvailableBusinessSeats(String.valueOf(Integer.parseInt(capacity.getAvailableBusinessSeats()) - Integer.parseInt(bookingRequest.getSeatCount())));
        } else if (bookingRequest.getSeatType().equalsIgnoreCase(String.valueOf(TypeOfSeat.ECONOMY))
                && Integer.parseInt(capacity.getAvailableEconomySeats()) - Integer.parseInt(bookingRequest.getSeatCount()) >= 0) {
            seatIds = assignSeats(seatIds, capacity.getAvailableEconomySeats(), Integer.parseInt(bookingRequest.getSeatCount()));
            capacity.setAvailableEconomySeats(String.valueOf(Integer.parseInt(capacity.getAvailableEconomySeats()) - Integer.parseInt(bookingRequest.getSeatCount())));
        } else {
            throw new BookingNotAvailableException("Booking Not Available Now");
        }
        capacityService.save(capacity);

        Integer fareAmount = Integer.parseInt(bookingRequest.getSeatCount()) *
                Integer.parseInt(fareService.getFareOfBooking(bookingRequest.getFlightId(), bookingRequest.getSeatType()));


        // Make Booking
        Booking booking = Booking.builder()
                .userId(bookingRequest.getUserId())
                .flightId(bookingRequest.getFlightId())
                .seatId(seatIds)
                .seatType(bookingRequest.getSeatType())
                .fare(String.valueOf(fareAmount))
                .status(String.valueOf(BookingStatus.BOOKED))
                .build();
        booking = bookingRepository.save(booking);

        return booking;
    }

    private String assignSeats(String seatIds, String availableEconomySeats, Integer count) {
        Integer avlSeats = Integer.parseInt(availableEconomySeats);
        for (int i = 0; i < count; i++) {
            seatIds += (avlSeats-i) + ",";
        }
        // {FLight_id + 20 +Premium}
        return seatIds;
    }

    public Booking cancelBooking(CancelBookingRequest cancelBookingRequest) {
        Booking booking = bookingRepository.findById(cancelBookingRequest.getBookingId()).get();
        booking.setStatus(BookingStatus.CANCELLED.toString());
        booking = bookingRepository.save(booking);
        return booking;
        // TODO : Make these cancelled seats available for booking
    }

    public List<Booking> getAllBooking() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings;
    }

    public List<Booking> findAllByFlightId(String flightId) {
        List<Booking> bookings = bookingRepository.findAllByFlightId(flightId);
        return bookings;
    }
}
