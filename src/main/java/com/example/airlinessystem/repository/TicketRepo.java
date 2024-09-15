package com.example.airlinessystem.repository;

import com.example.airlinessystem.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Long> {
    List<Ticket> findByFlightId(Long flightId);
    Optional<Ticket> findByTicketNumber(String ticketNumber);
    void deleteTicketByTicketNumber(String ticketNumber);
}
