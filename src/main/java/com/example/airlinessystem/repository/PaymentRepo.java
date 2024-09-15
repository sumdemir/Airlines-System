package com.example.airlinessystem.repository;


import com.example.airlinessystem.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment, Long> {
}
