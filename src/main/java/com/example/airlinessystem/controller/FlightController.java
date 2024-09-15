package com.example.airlinessystem.controller;


import com.example.airlinessystem.model.Flight;
import com.example.airlinessystem.model.mapper.FlightMapper;
import com.example.airlinessystem.model.request.FlightCreateRequest;
import com.example.airlinessystem.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    public FlightMapper flightMapper = FlightMapper.initialize();

    @PostMapping
    public ResponseEntity<FlightCreateRequest> addFlight(
            @RequestBody FlightCreateRequest flightDTO
    ) {
        FlightCreateRequest savedFlightDTO = flightService.createFlight(flightDTO);
        return new ResponseEntity<>(savedFlightDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightCreateRequest> getFlightById(
            @PathVariable Long id){
                FlightCreateRequest flightDTO = flightService.getFlightById(id);
                return new ResponseEntity<>(flightDTO, HttpStatus.OK);
    }

}
