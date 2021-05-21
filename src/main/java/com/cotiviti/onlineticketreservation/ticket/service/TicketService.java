package com.cotiviti.onlineticketreservation.ticket.service;

import com.cotiviti.onlineticketreservation.ticket.dto.TicketDto;

import java.math.BigDecimal;
import java.util.List;

public interface TicketService {
    TicketDto save(TicketDto ticketDto);

    TicketDto saveEventTickets(TicketDto ticketDto,Long eventId);

    TicketDto findByEventId(Long id);
}
