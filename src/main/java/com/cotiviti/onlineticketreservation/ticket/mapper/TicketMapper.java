package com.cotiviti.onlineticketreservation.ticket.mapper;

import com.cotiviti.onlineticketreservation.event.mapper.EventMapper;
import com.cotiviti.onlineticketreservation.ticket.dto.TicketDto;
import com.cotiviti.onlineticketreservation.ticket.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TicketMapper {
    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

    @Mapping(source = "eventId", target = "event.id")
    Ticket toEntity(TicketDto ticketDto);

    @Mapping(source = "event.id", target = "eventId")
    TicketDto toDto(Ticket ticket);
}
