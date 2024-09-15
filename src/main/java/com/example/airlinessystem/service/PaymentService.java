package com.example.airlinessystem.service;

import com.example.airlinessystem.model.Payment;
import com.example.airlinessystem.model.Ticket;
import com.example.airlinessystem.model.dto.route.request.PaymentRequest;
import com.example.airlinessystem.model.dto.route.request.PaymentResponse;
import com.example.airlinessystem.model.mapper.PaymentMapper;
import com.example.airlinessystem.repository.PaymentRepo;
import com.example.airlinessystem.repository.TicketRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepo paymentRepo;
    private final TicketRepo ticketRepo;
    private final PaymentMapper paymentMapper = PaymentMapper.INSTANCE;

    public PaymentResponse paymentTicket(PaymentRequest paymentRequest){
        Ticket ticket =ticketRepo.findByTicketNumber(paymentRequest.getTicketNumber())
                .orElseThrow( ()-> new RuntimeException("Ticket not found with ID:" + paymentRequest.getTicketNumber()));

        String cleanedCardNumber =cleanCardNumber(paymentRequest.getCardNumber());
        String maskedCardNumber = maskCardNumber(cleanedCardNumber);

        Payment payment = paymentMapper.toEntity(paymentRequest);
        payment.setTicket(ticket);
        payment.setCardNumber(maskedCardNumber);
        payment.setStatus("Sold");
        Payment savedPayment = paymentRepo.save(payment);



        PaymentResponse paymentResponse =paymentMapper.toDTO(savedPayment);
        paymentResponse.setTicketId(ticket.getId());
        paymentResponse.setTicketNumber(ticket.getTicketNumber());
        paymentResponse.setPassengerName(ticket.getPassengerName());
        paymentResponse.setPrice(ticket.getPrice());

        return paymentResponse;
    }

    private String cleanCardNumber(String cardNumber){
        return cardNumber.replaceAll("\\D", "");
    }

    private String maskCardNumber(String cardNumber){
        String cleanedCardNumber = cleanCardNumber(cardNumber);
        if(cleanedCardNumber.length() < 6 ){
            throw new IllegalArgumentException("Invalid card number length.");

        }
        String start = cleanedCardNumber.substring(0,3);
        String end = cleanedCardNumber.substring(cleanedCardNumber.length() - 3);
        return start + "******" + end;

    }
}
