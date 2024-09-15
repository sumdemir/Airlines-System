package com.example.airlinessystem.controller;


import com.example.airlinessystem.model.Airport;
import com.example.airlinessystem.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @PostMapping
    public ResponseEntity<Airport> addAirport(@RequestBody Airport airport){
        Airport newAirport = airportService.addAirport(airport);
        return ResponseEntity.ok(newAirport);
    }

    @GetMapping
    public ResponseEntity<List<Airport>> getAllAirports(){
        List<Airport> airports= airportService.getAllAirports();
        return ResponseEntity.ok(airports);

    }

    @GetMapping("/search")
    public ResponseEntity<List<Airport>> searchAirports(@RequestParam String keyword){
        List<Airport> airports =airportService.searchAirports(keyword);
        if(airports.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(airports);
        }
    }

//    @DeleteMapping("/name")
//    public ResponseEntity<Void> deleteAirportByName(@RequestParam String name) {
//        airportService.deleteAirportByName(name);
//        return ResponseEntity.noContent().build();
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAirportById(@PathVariable Long id) {
        try {
            airportService.deleteAirportById(id);
            return ResponseEntity.ok("Airport with ID " + id + " deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
