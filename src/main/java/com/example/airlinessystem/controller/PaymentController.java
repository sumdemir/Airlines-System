package com.example.airlinessystem.controller;

import com.example.airlinessystem.model.dto.route.request.PaymentRequest;
import com.example.airlinessystem.model.dto.route.request.PaymentResponse;
import com.example.airlinessystem.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponse> paymentTicket(
            @RequestBody PaymentRequest paymentRequest
            ){
        PaymentResponse paymentResponse= paymentService.paymentTicket(paymentRequest);
        return new ResponseEntity<>(paymentResponse, HttpStatus.CREATED);
    }
}
