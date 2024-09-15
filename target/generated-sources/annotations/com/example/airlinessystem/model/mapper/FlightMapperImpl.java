package com.example.airlinessystem.model.mapper;

import com.example.airlinessystem.model.Flight;
import com.example.airlinessystem.model.request.FlightCreateRequest;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-14T23:07:10+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
public class FlightMapperImpl implements FlightMapper {

    @Override
    public Flight toEntity(FlightCreateRequest flightDTO) {
        if ( flightDTO == null ) {
            return null;
        }

        Flight flight = new Flight();

        flight.setFlightNumber( flightDTO.getFlightNumber() );
        flight.setDepartureTime( flightDTO.getDepartureTime() );
        flight.setArrivalTime( flightDTO.getArrivalTime() );
        flight.setSeatCapacity( flightDTO.getSeatCapacity() );
        flight.setAvailableSeats( flightDTO.getAvailableSeats() );

        return flight;
    }

    @Override
    public FlightCreateRequest toDTO(Flight flight) {
        if ( flight == null ) {
            return null;
        }

        FlightCreateRequest flightCreateRequest = new FlightCreateRequest();

        flightCreateRequest.setFlightNumber( flight.getFlightNumber() );
        flightCreateRequest.setSeatCapacity( flight.getSeatCapacity() );
        flightCreateRequest.setAvailableSeats( flight.getAvailableSeats() );
        flightCreateRequest.setDepartureTime( flight.getDepartureTime() );
        flightCreateRequest.setArrivalTime( flight.getArrivalTime() );

        return flightCreateRequest;
    }
}
