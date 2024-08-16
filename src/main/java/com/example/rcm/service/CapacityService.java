package com.example.rcm.service;

import com.example.rcm.entity.Capacity;
import com.example.rcm.repository.CapacityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CapacityService {
    @Autowired
    CapacityRepository capacityRepository;

    public Capacity save(Capacity capacity) {
        return  capacityRepository.save(capacity);
    }

    public Capacity findById(String id) {
        return capacityRepository.findByFlightId(id);
    }

    public Capacity getByFlightId(String id) {
        return capacityRepository.findByFlightId(id);
    }

}
