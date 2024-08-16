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
public class Seat {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",
            strategy = "uuid")
    @JsonProperty("id")
    private String id;

    @JsonProperty("seat_type")
    @Column(name = "seat_type")
    private String seatType;

    @JsonProperty("seat_number")
    @Column(name = "seat_number")
    private String seatNumber;

    @JsonProperty("status")
    @Column(name = "status")
    private String status; // BOOKED or CANCELLED
}
