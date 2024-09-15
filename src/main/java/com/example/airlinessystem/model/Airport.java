package com.example.airlinessystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String iatacode;

    @OneToMany(
            mappedBy = "departureAirport",
            fetch = FetchType.LAZY
    )
    List<Route> departureRoutes;

    @OneToMany(
            mappedBy = "arrivalAirport"
    )
    List<Route> arrivalRoutes;

}
