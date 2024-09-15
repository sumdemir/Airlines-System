package com.example.airlinessystem.model.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FlightCreateRequest {

    private String flightNumber;
    private Long airlineId;
    private Long routeId;
    private int seatCapacity;
    private int availableSeats;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

}
