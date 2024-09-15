package com.example.airlinessystem.model.dto.route.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponse {
    private Long id;
    private Long ticketId;
    private String ticketNumber;
    private String passengerName;
    private double price;
    private String status;

    @Override
    public String toString(){
        return "PaymentResponse{" +
                "id=" + id +
                ", ticketNumber=" + ticketId +
                ", ticketNumber='" + ticketNumber + '\''+
                ", passengerName='" + passengerName + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                '}';
    }
}
