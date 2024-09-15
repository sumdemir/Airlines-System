package com.example.airlinessystem.service;

import com.example.airlinessystem.model.Airline;
import com.example.airlinessystem.repository.AirlineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineService {

    @Autowired
    private AirlineRepo airlineRepo;

    public Airline addAirline(Airline airline){
        return airlineRepo.save(airline);
    }


    public List<Airline> getAllAirlines(){
        return airlineRepo.findAll();
    }

    public List<Airline> searchAirlines(String keyword){
        return airlineRepo.findByNameContainingIgnoreCase(keyword);
    }
}
