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
public class Capacity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",
            strategy = "uuid")
    @JsonProperty("id")
    private String id;

    @JsonProperty("total_premium_seats")
    @Column(name = "total_premium_seats")
    private String totalPremiumSeats;

    @JsonProperty("available_premium_seats")
    @Column(name = "available_premium_seats")
    private String availablePremiumSeats;

    @JsonProperty("total_economy_seats")
    @Column(name = "total_economy_seats")
    private String totalEconomySeats;

    @JsonProperty("available_economy_seats")
    @Column(name = "available_economy_seats")
    private String availableEconomySeats;

    @JsonProperty("total_business_seats")
    @Column(name = "total_business_seats")
    private String totalBusinessSeats;

    @JsonProperty("available_business_seats")
    @Column(name = "available_business_seats")
    private String availableBusinessSeats;

    @JsonProperty("flight_id")
    @Column(name = "flight_id")
    private String flightId;

}
