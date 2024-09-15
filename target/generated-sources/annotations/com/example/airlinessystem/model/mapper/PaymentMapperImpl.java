package com.example.airlinessystem.model.mapper;

import com.example.airlinessystem.model.Payment;
import com.example.airlinessystem.model.dto.route.request.PaymentRequest;
import com.example.airlinessystem.model.dto.route.request.PaymentResponse;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-14T23:07:10+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
public class PaymentMapperImpl implements PaymentMapper {

    @Override
    public Payment toEntity(PaymentRequest paymentRequest) {
        if ( paymentRequest == null ) {
            return null;
        }

        Payment payment = new Payment();

        payment.setTicketNumber( paymentRequest.getTicketNumber() );
        payment.setCardNumber( paymentRequest.getCardNumber() );
        payment.setCardHolderName( paymentRequest.getCardHolderName() );
        payment.setExpirationDate( paymentRequest.getExpirationDate() );
        payment.setCVV( paymentRequest.getCVV() );

        return payment;
    }

    @Override
    public PaymentResponse toDTO(Payment payment) {
        if ( payment == null ) {
            return null;
        }

        PaymentResponse paymentResponse = new PaymentResponse();

        paymentResponse.setId( payment.getId() );
        paymentResponse.setTicketNumber( payment.getTicketNumber() );
        paymentResponse.setStatus( payment.getStatus() );

        return paymentResponse;
    }
}
