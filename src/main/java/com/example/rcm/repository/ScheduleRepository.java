package com.example.rcm.repository;

import com.example.rcm.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, String> {
    // List<Schedule> findAllByOriginAndDestinationAndStartTimeAndEndTime(String origin, String destination, String timeOfFly, String endTimeOfFly);

    List<Schedule> findAllByOriginAndDestinationAndStartTimeGreaterThanEqualAndEndTimeLessThanEqual(String origin, String destination, String timeOfFly, String endTimeOfFly);
}
