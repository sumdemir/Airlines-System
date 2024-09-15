package com.example.airlinessystem.service;

import com.example.airlinessystem.model.Flight;
import com.example.airlinessystem.model.Ticket;
import com.example.airlinessystem.model.dto.route.request.TicketCreateRequest;
import com.example.airlinessystem.model.mapper.TicketMapper;
import com.example.airlinessystem.repository.FlightRepo;
import com.example.airlinessystem.repository.TicketRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepo ticketRepo;
    private final FlightRepo flightRepo;
    private final TicketMapper ticketMapper =TicketMapper.initialize();

    public TicketCreateRequest createTicket(
            final TicketCreateRequest ticketDTO
    ){
        final Flight flight = flightRepo.findById(ticketDTO.getFlightId())
                .orElseThrow( () -> new RuntimeException("Flight not found."));

        final Ticket ticket =ticketMapper.toEntity(ticketDTO);

        ticket.setFlight(flight);

        String generatedTicketNumber = generateTicketNumber();
        ticket.setTicketNumber(generatedTicketNumber);

        //BURADA RESPONSE DÖNDÜRÜYORUM
        final Ticket savedTicket = ticketRepo.save(ticket);
        TicketCreateRequest ticket1= ticketMapper.toDTO(savedTicket);
        ticket1.setFlightId(savedTicket.getFlight().getId());
        ticket1.setTicketNumber(savedTicket.getTicketNumber().toString());

        return ticket1;

    }

    private String generateTicketNumber(){
        String chars="ABCDEFGHJKLMNOPRSTUVWXYZ0123456789";
                StringBuilder ticketNumber= new StringBuilder();
        Random random = new Random();

        ticketNumber.append(chars.charAt(random.nextInt(26)));

        for (int i=0; i<5; i++){
            ticketNumber.append(chars.charAt(random.nextInt(chars.length())));

        }

        return ticketNumber.toString();
    }

    public TicketCreateRequest getTicketByTicketNumber(String ticketNumber){
        Ticket ticket = ticketRepo.findByTicketNumber(ticketNumber)
                .orElseThrow( ()-> new RuntimeException("Ticket not found."));

        return ticketMapper.toDTO(ticket);
    }

    public TicketCreateRequest getTicketById(Long id){
        Ticket ticket= ticketRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Ticket can not found with ID:"+ id));

        return ticketMapper.toDTO(ticket);
    }

    public void deleteTicketById(Long id){
        Ticket ticket =ticketRepo.findById(id)
                .orElseThrow( ()-> new RuntimeException("Ticket not found with ID:"+ id));

        ticketRepo.deleteById(id);
    }

    @Transactional
    public void deleteTicketByTicketNumber(String ticketNumber){
        Ticket ticket =ticketRepo.findByTicketNumber(ticketNumber)
                .orElseThrow( ()-> new RuntimeException("Ticket not found with Ticket Number:"+ ticketNumber));

        ticketRepo.deleteTicketByTicketNumber(ticketNumber);
    }





}
