package com.example.airlinessystem.model.dto.route.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketCreateRequest {

    private Long flightId;
    private String passengerName;
    private String seatNumber;
    private Double price;
    private String ticketNumber;
}
