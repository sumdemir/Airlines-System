package com.example.airlinessystem.repository;

import com.example.airlinessystem.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepo extends JpaRepository<Flight, Long> {

    List<Flight> findByFlightNumber(String flightNumber);

}
