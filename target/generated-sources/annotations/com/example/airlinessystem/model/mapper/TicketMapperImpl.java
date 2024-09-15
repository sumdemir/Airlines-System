package com.example.airlinessystem.model.mapper;

import com.example.airlinessystem.model.Flight;
import com.example.airlinessystem.model.Ticket;
import com.example.airlinessystem.model.dto.route.request.TicketCreateRequest;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-14T23:07:10+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
public class TicketMapperImpl implements TicketMapper {

    @Override
    public Ticket toEntity(TicketCreateRequest ticketDTO) {
        if ( ticketDTO == null ) {
            return null;
        }

        Ticket ticket = new Ticket();

        ticket.setPassengerName( ticketDTO.getPassengerName() );
        ticket.setSeatNumber( ticketDTO.getSeatNumber() );
        if ( ticketDTO.getPrice() != null ) {
            ticket.setPrice( ticketDTO.getPrice() );
        }
        ticket.setTicketNumber( ticketDTO.getTicketNumber() );

        return ticket;
    }

    @Override
    public TicketCreateRequest toDTO(Ticket ticket) {
        if ( ticket == null ) {
            return null;
        }

        TicketCreateRequest ticketCreateRequest = new TicketCreateRequest();

        ticketCreateRequest.setFlightId( ticketFlightId( ticket ) );
        ticketCreateRequest.setTicketNumber( ticket.getTicketNumber() );
        ticketCreateRequest.setPassengerName( ticket.getPassengerName() );
        ticketCreateRequest.setSeatNumber( ticket.getSeatNumber() );
        ticketCreateRequest.setPrice( ticket.getPrice() );

        return ticketCreateRequest;
    }

    private Long ticketFlightId(Ticket ticket) {
        if ( ticket == null ) {
            return null;
        }
        Flight flight = ticket.getFlight();
        if ( flight == null ) {
            return null;
        }
        Long id = flight.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
