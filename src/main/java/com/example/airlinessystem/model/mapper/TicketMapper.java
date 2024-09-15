package com.example.airlinessystem.model.mapper;

import com.example.airlinessystem.model.Ticket;
import com.example.airlinessystem.model.dto.route.request.TicketCreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TicketMapper {

    @Mapping(target = "flight", ignore = true)
    Ticket toEntity(
            TicketCreateRequest ticketDTO
    );

    @Mapping(target = "flightId", source = "flight.id")
    @Mapping(target = "ticketNumber", source = "ticketNumber")
    TicketCreateRequest toDTO(Ticket ticket);

    static TicketMapper initialize(){
        return Mappers.getMapper(TicketMapper.class);
    }


}
