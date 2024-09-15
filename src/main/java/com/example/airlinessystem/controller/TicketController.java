package com.example.airlinessystem.controller;

import com.example.airlinessystem.model.Ticket;
import com.example.airlinessystem.model.dto.route.request.TicketCreateRequest;
import com.example.airlinessystem.model.mapper.TicketMapper;
import com.example.airlinessystem.model.mapper.TicketMapperImpl;
import com.example.airlinessystem.model.request.FlightCreateRequest;
import com.example.airlinessystem.service.TicketService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;
    public TicketMapper ticketMapper = TicketMapper.initialize();

    @PostMapping
    public ResponseEntity<TicketCreateRequest> addTicket(
            @RequestBody TicketCreateRequest ticketDTO
    ){
        TicketCreateRequest savedTicketDTO=ticketService.createTicket(ticketDTO);
        return new ResponseEntity<>(savedTicketDTO, HttpStatus.CREATED);
    }

    @GetMapping("/ticket/{ticketNumber}")
    public ResponseEntity<TicketCreateRequest> getTicketByNumber(
            @PathVariable String ticketNumber){
        TicketCreateRequest ticketDTO = ticketService.getTicketByTicketNumber(ticketNumber);
        return new ResponseEntity<>(ticketDTO, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<TicketCreateRequest> getTicketById(
            @PathVariable Long id
    ){
        TicketCreateRequest ticketDTO1= ticketService.getTicketById(id);
        return new ResponseEntity<>(ticketDTO1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTicketById(@PathVariable Long id){
        try{
            ticketService.deleteTicketById(id);
            return new ResponseEntity<>("Ticket deleted succesfully.", HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/ticket/{ticketNumber}")
    public ResponseEntity<String > deleteTicketByTicketNumber(@PathVariable String ticketNumber){
        try{
            ticketService.deleteTicketByTicketNumber(ticketNumber);
            return new ResponseEntity<>("Ticket cancelled successfully by Ticket Number.", HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
