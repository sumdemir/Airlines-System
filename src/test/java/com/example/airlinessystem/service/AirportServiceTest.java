package com.example.airlinessystem.service;

import com.example.airlinessystem.model.Airport;
import com.example.airlinessystem.repository.AirportRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AirportServiceTest {

    @Mock
    private AirportRepo airportRepo;

    @InjectMocks
    private AirportService airportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddAirport() {

        Airport airport = new Airport();
        airport.setId(1L);
        airport.setName("Test Airport");
        airport.setIatacode("Test Code");

        when(airportRepo.save(any(Airport.class))).thenReturn(airport);

        Airport result = airportService.addAirport(airport);

        assertEquals(1L, result.getId());
        assertEquals("Test Airport", result.getName());

        verify(airportRepo, times(1)).save(any(Airport.class));

    }

    @Test
    public void testGetAllAirports() {

        Airport airport1 = new Airport();
        airport1.setId(1L);
        airport1.setName("TestAirport 1");
        airport1.setIatacode("TC1");

        Airport airport2 = new Airport();
        airport2.setId(2L);
        airport2.setName("TestAirport 2");
        airport2.setIatacode("TC2");

        List<Airport> mockAirports = Arrays.asList(airport1, airport2);

        when(airportRepo.findAll()).thenReturn(mockAirports);

        List<Airport> result = airportService.getAllAirports();

        assertEquals(2, result.size());
        assertEquals("TestAirport 1", result.get(0).getName());
        assertEquals("TestAirport 2", result.get(1).getName());
        assertEquals("TC1", result.get(0).getIatacode());
        assertEquals("TC2", result.get(1).getIatacode());

        verify(airportRepo, times(1)).findAll();
    }

    @Test
     public void testSearchAirports() {

        String keyword = "Airp";
        Airport airport1 = new Airport();
        airport1.setId(1L);
        airport1.setIatacode("TC1");
        airport1.setName("TestAirport 1");

        Airport airport2 = new Airport();
        airport2.setId(2L);
        airport2.setIatacode("TC2");
        airport2.setName("TestAirport 2");

        List<Airport> mockAirports =Arrays.asList(airport1, airport2);

        when(airportRepo.findByNameContainingIgnoreCase(keyword)).thenReturn(mockAirports);

        List<Airport> res = airportService.searchAirports(keyword);

        assertEquals(2, res.size());
        assertEquals("TC1", res.get(0).getIatacode());
        assertEquals("TC2", res.get(1).getIatacode());
        assertEquals("TestAirport 1", res.get(0).getName());
        assertEquals("TestAirport 2", res.get(1).getName());

        verify(airportRepo, times(1)).findByNameContainingIgnoreCase(keyword);

    }

    @Test
    public void testDeleteAirportById() {
        long airportId = 1L;
        Airport mockAirport = new Airport();
        mockAirport.setId(airportId);

        when(airportRepo.findById(airportId)).thenReturn(Optional.of(mockAirport));

        airportService.deleteAirportById(airportId);

        verify(airportRepo, times(1)).deleteById(airportId);
    }

    @Test
    public void testDeleteAirportByIdWhenNotExist(){
        long airportId = 1L;

        when(airportRepo.findById(airportId)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> {
            airportService.deleteAirportById(airportId);
                });

        assertEquals("Airport with ID "+airportId +
                " not found", ex.getMessage()); //STRING İÇLERİ ÇOK HASSAS BOŞLUKLAR BİLE ÖNEMLİ

        verify(airportRepo, never()).deleteById(anyLong());
    }


























}