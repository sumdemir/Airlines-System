package com.example.airlinessystem.model;

import com.example.airlinessystem.service.TicketService;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
//    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    @JoinColumn(name="ticketNumber", nullable = false)
    private String ticketNumber;


    private String cardNumber;
    private String cardHolderName;
    private String expirationDate;
    private String CVV;
    private String status;

}
