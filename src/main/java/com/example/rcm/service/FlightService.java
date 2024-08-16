package com.example.rcm.service;

import com.example.rcm.entity.Booking;
import com.example.rcm.entity.Capacity;
import com.example.rcm.entity.Flight;
import com.example.rcm.entity.Schedule;
import com.example.rcm.exception.FlightNotFoundException;
import com.example.rcm.model.CancelBookingRequest;
import com.example.rcm.model.FlightCancelRequest;
import com.example.rcm.model.FlightRequest;
import com.example.rcm.model.FlightResponse;
import com.example.rcm.model.FlightStatus;
import com.example.rcm.model.FlightUpdateRequest;
import com.example.rcm.repository.FlightRepository;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    SchdeuleService schdeuleService;

    @Autowired
    CapacityService capacityService;

    @Autowired
    BookingService bookingService;

    @Autowired
    FlightRepository flightrepository;

    public FlightResponse createFlight(FlightRequest flightRequest) {
        Schedule schedule = Schedule.builder()
                .startTime(flightRequest.getStartTime())
                .endTime(flightRequest.getEndTime())
                .origin(flightRequest.getOrigin())
                .destination(flightRequest.getDestination())
                .build();

        Capacity capacity = Capacity.builder()
                .availableBusinessSeats(flightRequest.getTotalBusinessSeats())
                .totalBusinessSeats(flightRequest.getTotalBusinessSeats())
                .availableEconomySeats(flightRequest.getTotalEconomySeats())
                .totalEconomySeats(flightRequest.getTotalEconomySeats())
                .availablePremiumSeats(flightRequest.getTotalPremiumSeats())
                .totalPremiumSeats(flightRequest.getTotalPremiumSeats())
                .build();

        Flight flight = Flight.builder()
                .airlineId(flightRequest.getAirlineId())
                .baseFare(flightRequest.getBaseFare())
                .status(String.valueOf(FlightStatus.BOOKED))
                .build();
        flight = flightrepository.save(flight);

        schedule.setFlightId(flight.getId());
        schedule = schdeuleService.save(schedule);

        capacity.setFlightId(flight.getId());
        capacity = capacityService.save(capacity);

        flight.setCapacityId(capacity.getId());
        flight.setScheduleId(schedule.getId());
        flight = flightrepository.save(flight);

        FlightResponse flightResponse = FlightResponse.builder()
                .flight(flight)
                .capacity(capacity)
                .schedule(schedule)
                .build();
        return flightResponse;
        // TODO : Do Not Let Create New Flights If Some AirlineId already has an overlapping schedule
    }

    public List<FlightResponse> getAllFlightByOriginAndDestinationAndDataOfFly
            (String origin, String destination, String timeOfFly, String endTimeOfFly) {
        List<Schedule> schedules = schdeuleService
                .findAllAvailableFlightIds(origin, destination, timeOfFly, endTimeOfFly);
        List<FlightResponse> flightResponseList = new ArrayList<>();
        for (Schedule schedule : schedules) {
            FlightResponse flightRes = FlightResponse.builder()
                    .schedule(schedule)
                    .flight(flightrepository.findById(schedule.getFlightId()).get())
                    .capacity(capacityService.findById(schedule.getFlightId()))
                    .build();
            flightResponseList.add(flightRes);
        }
        return flightResponseList;
    }

    public String getBaseFareByFlightId(String flightId) {
        Optional<Flight> flight = flightrepository.findById(flightId);
        if (!flight.isPresent()) {
            throw new FlightNotFoundException("Flight Not Found Exception");
        }
        return flightrepository.findById(flightId).get().getBaseFare();
    }

    public Flight getFlightById(String flightId) {
        Optional<Flight> flight = flightrepository.findById(flightId);
        if (!flight.isPresent()) {
            throw new FlightNotFoundException("Flight Not Found Exception");
        }
        return flightrepository.findById(flightId).get();
    }

    public FlightResponse updateFlight(FlightUpdateRequest flightUpdateRequest) {
        //TODO :: Add validations over flightUpdateRequest
        Flight flight = getFlightById(flightUpdateRequest.getFlightId());
        Schedule schedule = schdeuleService.findById(flight.getScheduleId());
        if (flight != null) {
            schedule.setStartTime(flightUpdateRequest.getStartTime());
            schedule.setEndTime(flightUpdateRequest.getEndTime());
            schdeuleService.save(schedule);
        }
        return getFlightResponse(flight);
    }

    public FlightResponse cancelFlight(FlightCancelRequest flightCancelRequest) {
        Flight flight = getFlightById(flightCancelRequest.getFlightId());
        List<Booking> bookings = bookingService.findAllByFlightId(flightCancelRequest.getFlightId());
        for (Booking booking : bookings){
            bookingService.cancelBooking(CancelBookingRequest.builder().bookingId(booking.getId()).build());
        }
        flight.setStatus(FlightStatus.CANCELLED.toString());
        flightrepository.save(flight);
        return getFlightResponse(flight);
    }

    public List<Flight> getAllFlights() {
        List<Flight> flights = flightrepository.findAll();
        return flights;
    }

    private FlightResponse getFlightResponse(Flight flight) {
        return FlightResponse.builder()
                .schedule(schdeuleService.findById(flight.getScheduleId()))
                .flight(flight)
                .capacity(capacityService.findById(flight.getId()))
                .build();
    }
}

