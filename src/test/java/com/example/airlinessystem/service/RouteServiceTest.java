package com.example.airlinessystem.service;

import com.example.airlinessystem.model.Airport;
import com.example.airlinessystem.model.Route;
import com.example.airlinessystem.model.dto.route.request.RouteCreateRequest;
import com.example.airlinessystem.repository.AirportRepo;
import com.example.airlinessystem.repository.RouteRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RouteServiceTest {

    @InjectMocks
    private RouteService routeService;

    @Mock
    private RouteRepo routeRepo;

    @Mock
    private AirportRepo airportRepo;

    @Test
    void givenValidRequest_whenCreateRoute_thenReturnRoute() {

        //Given
        final RouteCreateRequest routeCreateRequest = new RouteCreateRequest();
        routeCreateRequest.setDepartureAirportId(1L);
        routeCreateRequest.setArrivalAirportId(2L);

        final Airport departureAirport = new Airport();
        departureAirport.setId(1L);

        final Airport arrivalAirport = new Airport();
        arrivalAirport.setId(2L);

        final Route route = new Route();
        route.setId(1L);
        route.setDepartureAirport(departureAirport);
        route.setArrivalAirport(arrivalAirport);

        //When
        Mockito.when(airportRepo.findById(1L))
                .thenReturn(Optional.of(departureAirport));

        Mockito.when(airportRepo.findById(2L))
                .thenReturn(Optional.of(arrivalAirport));

        Mockito.when(routeRepo.save(Mockito.any(Route.class)))
                .thenReturn(route);

        //Then
        final Route response = routeService.createRoute(routeCreateRequest);

        Mockito.verify(airportRepo, Mockito.times(2))
                .findById(Mockito.any(Long.class));

        Mockito.verify(routeRepo, Mockito.times(1))
                .save(Mockito.any(Route.class));

        Assertions.assertNotNull(response.getId());
        Assertions.assertNotNull(route.getArrivalAirport());
        Assertions.assertNotNull(route.getDepartureAirport());

    }

    @Test
    void testGetAllRoutes() {
        Route route1 = new Route();
        route1.setId(1L);
        Route route2 = new Route();
        route2.setId(2L);

        List<Route> routes = Arrays.asList(route1, route2);

        Mockito.when(routeRepo.findAll()).thenReturn(routes);

        List<Route> result = routeService.getAllRoutes();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(1L, result.get(0).getId());
        Assertions.assertEquals(2L, result.get(1).getId());

        Mockito.verify(routeRepo, Mockito.times(1)).findAll();

    }

    @Test
    void testGetRouteById() {

        // Given
        final Route route = new Route();
        route.setId(1L);

        // When
        Mockito.when(routeRepo.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(route));

        // Then
        final Route response = routeService.getRouteById(1L);

        Mockito.verify(routeRepo, Mockito.times(1))
                .findById(Mockito.anyLong());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(1L, response.getId());

    }

    @Test
    void deleteRouteById() {

        final Long routeId = 1L;

        routeService.deleteRouteById(routeId);

        Mockito.verify(routeRepo, Mockito.times(1)).deleteById(routeId);


    }
}