package com.example.airlinessystem.service;


import com.example.airlinessystem.model.Airport;
import com.example.airlinessystem.model.Route;
import com.example.airlinessystem.model.dto.route.request.RouteCreateRequest;
import com.example.airlinessystem.repository.AirportRepo;
import com.example.airlinessystem.repository.RouteRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepo routeRepo;
    private final AirportRepo airportRepo;

    public Route createRoute(
            final RouteCreateRequest request
    ){
        final Airport departureAirport = airportRepo
                .findById(request.getDepartureAirportId())
                .orElseThrow(() -> new RuntimeException("Airport not found with given id: " +
                        request.getDepartureAirportId()));

        final Airport arrivalAirport = airportRepo
                .findById(request.getArrivalAirportId())
                .orElseThrow(() -> new RuntimeException("Airport not found with given id: " +
                        request.getDepartureAirportId()));

        final Route route = Route.builder()
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .build();

        return routeRepo.save(route);
    }

    public List<Route> getAllRoutes(){
        return routeRepo.findAll();
    }

    public Route getRouteById(Long id){
        return routeRepo.findById(id).orElse(null);
    }

    public void deleteRouteById(Long id){
        routeRepo.deleteById(id);
    }

}
