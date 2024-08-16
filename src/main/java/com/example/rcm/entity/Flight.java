package com.example.rcm.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",
            strategy = "uuid")
    @JsonProperty("id")
    private String id;

    @JsonProperty("airline_id")
    @Column(name = "airline_id")
    private String airlineId; // As one flight will have multiple journeys throughout the day

    @JsonProperty("schedule_id")
    @Column(name = "schdeule_id")
    private String scheduleId;

    @JsonProperty("capacity_id")
    @Column(name = "capacity_id")
    private String capacityId;

    @JsonProperty("base_fare")
    @Column(name = "base_fare")
    private String baseFare;

    @JsonProperty("status")
    @Column(name = "status")
    private String status; // BOOKED or CANCELLED
}
