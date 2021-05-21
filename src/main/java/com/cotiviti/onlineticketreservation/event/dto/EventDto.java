package com.cotiviti.onlineticketreservation.event.dto;

import com.cotiviti.onlineticketreservation.enums.EventType;
import com.cotiviti.onlineticketreservation.ticket.dto.TicketDto;
import com.cotiviti.onlineticketreservation.ticket.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    private Long id;
    private String fromDestination;
    private String toDestination;
    @Enumerated(value = EnumType.STRING)
    private EventType eventType;
    private Integer totalNumberOfTickets;
    private Integer totalAvailableTickets;
    private Date departureDate;
    private TicketDto ticketDto;
}
