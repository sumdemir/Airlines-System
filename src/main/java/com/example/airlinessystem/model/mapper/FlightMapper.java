package com.example.airlinessystem.model.mapper;

import com.example.airlinessystem.model.request.FlightCreateRequest;
import com.example.airlinessystem.model.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FlightMapper {

    @Mapping(target = "airline", ignore = true)
    @Mapping(target = "route", ignore = true)

    Flight toEntity(
            FlightCreateRequest flightDTO
    );

    FlightCreateRequest toDTO(Flight flight);

    static FlightMapper initialize() {
        return Mappers.getMapper(FlightMapper.class);
    }

}
