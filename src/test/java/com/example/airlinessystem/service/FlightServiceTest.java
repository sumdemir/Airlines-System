package com.example.airlinessystem.service;

import com.example.airlinessystem.model.Airline;
import com.example.airlinessystem.model.Flight;
import com.example.airlinessystem.model.Route;
import com.example.airlinessystem.model.mapper.FlightMapper;
import com.example.airlinessystem.model.request.FlightCreateRequest;
import com.example.airlinessystem.repository.AirlineRepo;
import com.example.airlinessystem.repository.FlightRepo;
import com.example.airlinessystem.repository.RouteRepo;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class FlightServiceTest {

    @InjectMocks
    private FlightService flightService;

    @Mock
    private FlightRepo flightRepo;

    @Mock
    private RouteRepo routeRepo;

    @Mock
    private AirlineRepo airlineRepo;

    @Test
    public void testCreateFlight() {

        final FlightCreateRequest flightCreateRequest = new FlightCreateRequest();
        flightCreateRequest.setAirlineId(1L);
        flightCreateRequest.setRouteId(1L);

        final Airline airline = new Airline();
        airline.setId(1L);

        final Route route = new Route();
        route.setId(1L);

        final Flight flight = new Flight();
        flight.setId(1L);
        flight.setAirline(airline);
        flight.setRoute(route);

        //When
        Mockito.when(airlineRepo.findById(1L))
                .thenReturn(Optional.of(airline));

        Mockito.when(routeRepo.findById(1L))
                .thenReturn(Optional.of(route));

        Mockito.when(flightRepo.save(Mockito.any(Flight.class))).thenReturn(flight);

        //Then
        final FlightCreateRequest response = flightService.createFlight(flightCreateRequest);

        Mockito.verify(airlineRepo, Mockito.times(1))
                .findById(Mockito.any(Long.class));

        Mockito.verify(routeRepo, Mockito.times(1))
                .findById(Mockito.any(Long.class));

        Assertions.assertEquals(flightCreateRequest.getAirlineId(), response.getAirlineId());
        Assertions.assertEquals(flightCreateRequest.getRouteId(), response.getRouteId());

    }

    @Test
    void testGetFlightById() {
        final Airline airline = new Airline();
        airline.setId(1L);

        final Route route = new Route();
        route.setId(1L);

        final Flight flight = new Flight();
        flight.setId(1L);
        flight.setAirline(airline);
        flight.setRoute(route);

        final FlightCreateRequest flightCreateRequest = new FlightCreateRequest();
        flightCreateRequest.setAirlineId(airline.getId());
        flightCreateRequest.setRouteId(route.getId());

        Mockito.when(flightRepo.findById(1L))
                .thenReturn(Optional.of(flight));

        final FlightCreateRequest response = flightService.getFlightById(1L);

        Mockito.verify(flightRepo, Mockito.times(1))
                .findById(1L);

        Assertions.assertEquals(flight.getId(), response.getAirlineId());
        Assertions.assertEquals(flight.getRoute().getId(), response.getRouteId());
    }
}