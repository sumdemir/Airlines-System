package com.example.airlinessystem.service;

import com.example.airlinessystem.model.Airline;
import com.example.airlinessystem.model.Flight;
import com.example.airlinessystem.model.Route;
import com.example.airlinessystem.model.mapper.FlightMapper;
import com.example.airlinessystem.model.request.FlightCreateRequest;
import com.example.airlinessystem.repository.AirlineRepo;
import com.example.airlinessystem.repository.FlightRepo;
import com.example.airlinessystem.repository.RouteRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepo flightRepo;
    private final AirlineRepo airlineRepo;
    private final RouteRepo routeRepo;
    private final FlightMapper flightMapper = FlightMapper.initialize();

    public FlightCreateRequest createFlight(
            final FlightCreateRequest flightDTO
    ) {
        final Airline airline = airlineRepo.findById(flightDTO.getAirlineId())
                .orElseThrow(() -> new RuntimeException("Airline not found."));

        final Route route = routeRepo.findById(flightDTO.getRouteId())
                .orElseThrow(() -> new RuntimeException("Route not found."));

        final Flight flight = flightMapper.toEntity(flightDTO);

        flight.setAirline(airline);
        flight.setRoute(route);

        final Flight savedFlight = flightRepo.save(flight);

         FlightCreateRequest flight1 =flightMapper.toDTO(savedFlight);
         flight1.setAirlineId(savedFlight.getAirline().getId());
         flight1.setRouteId(savedFlight.getRoute().getId());

        return flight1;
    }

    public FlightCreateRequest getFlightById(Long id){
        Flight flight = flightRepo.findById(id)
                .orElseThrow( ()-> new RuntimeException("Flight not found with ID:" + id));

        FlightCreateRequest flightResponse = flightMapper.toDTO(flight);

        flightResponse.setAirlineId(flight.getAirline().getId());
        flightResponse.setRouteId(flight.getRoute().getId());

        return flightResponse;
    }

}
