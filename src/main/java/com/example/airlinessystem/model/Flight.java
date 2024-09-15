package com.example.airlinessystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "airlineId", nullable = false)
    private Airline airline;

    @ManyToOne
    @JoinColumn(name = "routeId", nullable = false)
    private Route route;

    private String flightNumber;

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    private int seatCapacity;
    private int availableSeats;



  }
