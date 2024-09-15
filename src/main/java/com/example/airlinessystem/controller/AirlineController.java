package com.example.airlinessystem.controller;


import com.example.airlinessystem.model.Airline;
import com.example.airlinessystem.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airlines")
public class AirlineController {

    @Autowired
    private AirlineService airlineService;

    @PostMapping
    public ResponseEntity<Airline> addAirline(@RequestBody Airline airline){
        Airline newAirline = airlineService.addAirline(airline);
        return ResponseEntity.ok(newAirline);
    }

    @GetMapping
    public ResponseEntity<List<Airline>> getAllAirlines(){
        List<Airline> airlines=airlineService.getAllAirlines();
        return ResponseEntity.ok(airlines);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Airline>> searchAirlines(@RequestParam String keyword) {
        List<Airline> airlines = airlineService.searchAirlines(keyword);
        if (airlines.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(airlines);
        }
    }




}

