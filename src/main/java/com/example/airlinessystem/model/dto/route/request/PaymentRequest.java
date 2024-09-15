package com.example.airlinessystem.model.dto.route.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {

//    private Long ticketId;
    private String ticketNumber;
    private String cardNumber;
    private String cardHolderName;
    private String expirationDate;
    private String CVV;

    @Override
    public String toString(){
        return "PaymentRequest{" +
                "ticketNumber=" + ticketNumber +
                ", cardHolderName='" + cardHolderName + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", CVV='***'" + '}';

    }

}
