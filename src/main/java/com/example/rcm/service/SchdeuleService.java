package com.example.rcm.service;

import com.example.rcm.entity.Schedule;
import com.example.rcm.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchdeuleService {
    @Autowired
    ScheduleRepository scheduleRepository;

    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> findAllAvailableFlightIds
            (String origin, String destination, String timeOfFly, String endTimeOfFly) {
       List<Schedule> schedules = scheduleRepository
               .findAllByOriginAndDestinationAndStartTimeGreaterThanEqualAndEndTimeLessThanEqual(origin,destination,timeOfFly,endTimeOfFly);
       return schedules;
    }

    public Schedule findById(String scheduleId) {
        return scheduleRepository.findById(scheduleId).get();
    }
}
