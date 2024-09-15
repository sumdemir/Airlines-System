package com.example.airlinessystem.model.dto.route.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteCreateRequest {
    private Long departureAirportId;
    private Long arrivalAirportId;
}
