package com.example.airlinessystem.service;

import com.example.airlinessystem.model.Airport;
import com.example.airlinessystem.repository.AirportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    @Autowired
    private AirportRepo airportRepo;

    public Airport addAirport(Airport airport){
        return airportRepo.save(airport);
    }

    public List<Airport> getAllAirports(){
        return airportRepo.findAll();
    }

    public List<Airport> searchAirports(String keyword){
        return airportRepo.findByNameContainingIgnoreCase(keyword);
    }

    public void deleteAirportById(Long id) {
        Optional<Airport> airport = airportRepo.findById(id);
        if (airport.isPresent()) {
            airportRepo.deleteById(id);
        } else {
            throw new RuntimeException("Airport with ID " + id + " not found");
        }
    }
}
