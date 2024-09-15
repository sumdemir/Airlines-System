package com.example.airlinessystem.model.mapper;

import com.example.airlinessystem.model.Payment;
import com.example.airlinessystem.model.dto.route.request.PaymentRequest;
import com.example.airlinessystem.model.dto.route.request.PaymentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentMapper {

    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    Payment toEntity(PaymentRequest paymentRequest);
    PaymentResponse toDTO(Payment payment);
}
