package com.example.rcm.repository;

import com.example.rcm.entity.Capacity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapacityRepository extends JpaRepository<Capacity,String> {

    Capacity findByFlightId(String id);
}
