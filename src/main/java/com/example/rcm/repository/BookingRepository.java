package com.example.rcm.repository;

import com.example.rcm.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
    Booking findByUserIdAndFlightIdAndId(String userId, String flightId, String bookingId);

    List<Booking> findAllByFlightId(String flightId);
}
