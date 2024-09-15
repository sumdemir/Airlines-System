package com.example.airlinessystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    private String passengerName;

    private String seatNumber;
    private double price;
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, unique = true)
    private String ticketNumber;

    private String status;
}
