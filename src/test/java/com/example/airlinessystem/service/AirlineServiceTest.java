package com.example.airlinessystem.service;

import com.example.airlinessystem.model.Airline;
import com.example.airlinessystem.repository.AirlineRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;
import java.util.List;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AirlineServiceTest {

    @InjectMocks
    private AirlineService airlineService;

    @Mock
    private AirlineRepo airlineRepo;

    @Test
    public void givenValidRequest_whenCreateAirline_thenReturnAirline() {

        //Given
        Airline airlineCreateRequest = new Airline();
        airlineCreateRequest.setName("Test Airline");
        airlineCreateRequest.setCode("Test Code");

        Airline createdAirline = new Airline();
        createdAirline.setId(5L);
        createdAirline.setName("Test Airline");
        createdAirline.setCode("Test Code");

        //When (Senaryo)
        Mockito.when(airlineRepo.save(Mockito.any(Airline.class)))
                .thenReturn(createdAirline);

        final Airline response = airlineService.addAirline(airlineCreateRequest);

        // Then
        Mockito.verify(airlineRepo,Mockito.times(1))
                .save(Mockito.any(Airline.class));

        Assertions.assertEquals(airlineCreateRequest.getName(), response.getName());
    }

    @Test
    public void testGetAllAirlines() {

        Airline airline1 = new Airline();
        airline1.setId(1L);
        airline1.setName("TestAirline 1");
        airline1.setCode("TestCode 1");

        Airline airline2 = new Airline();
        airline2.setId(2L);
        airline2.setName("TestAirline 2");
        airline2.setCode("TestCode 2");

        List<Airline> mockAirlines = Arrays.asList(airline1, airline2);

        Mockito.when(airlineRepo.findAll()).thenReturn(mockAirlines);

        List<Airline> result = airlineService.getAllAirlines();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("TestAirline 1", result.get(0).getName());
        Assertions.assertEquals("TestAirline 2", result.get(1).getName());

        Mockito.verify(airlineRepo, Mockito.times(1)).findAll();

    }

    @Test
    public void testSearchAirlines() {

        String keyword = "Air";
        Airline airline1 = new Airline();
        airline1.setId(1L);
        airline1.setName("TestAirline One");
        airline1.setCode("A1");

        Airline airline2 = new Airline();
        airline2.setId(1L);
        airline2.setName("TestAirline Two");
        airline2.setCode("A2");

        List<Airline> mockAirlines = Arrays.asList(airline1, airline2);

        Mockito.when(airlineRepo.findByNameContainingIgnoreCase(keyword)).thenReturn(mockAirlines);

        List<Airline> result = airlineService.searchAirlines(keyword);

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("TestAirline One", result.get(0).getName());
        Assertions.assertEquals("TestAirline Two", result.get(1).getName());

        Mockito.verify(airlineRepo, Mockito.times(1)).findByNameContainingIgnoreCase(keyword);

    }
}