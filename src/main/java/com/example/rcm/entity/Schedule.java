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
public class Schedule {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",
            strategy = "uuid")
    @JsonProperty("id")
    private String id;

    @JsonProperty("start_time")
    @Column(name = "start_time")
    private String startTime;

    @JsonProperty("end_time")
    @Column(name = "end_time")
    private String endTime;

    @JsonProperty("origin")
    @Column(name = "origin")
    private String origin;

    @JsonProperty("destination")
    @Column(name = "destination")
    private String destination;

    @JsonProperty("flight_id")
    @Column(name = "flight_id")
    private String flightId;
}
