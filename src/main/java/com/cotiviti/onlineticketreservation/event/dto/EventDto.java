package com.cotiviti.onlineticketreservation.event.dto;

import com.cotiviti.onlineticketreservation.enums.EventType;
import com.cotiviti.onlineticketreservation.ticket.dto.TicketDto;
import com.cotiviti.onlineticketreservation.ticket.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    private Long id;
    @NotNull(message = "please include to destination")
    private String fromDestination;
    @NotBlank(message = "please include to destination")
    private String toDestination;
    @Enumerated(value = EnumType.STRING)
    private EventType eventType;
    @NotNull(message = "enter ticket quantity")
    private Integer totalNumberOfTickets;
    private Integer totalAvailableTickets;
    @NotNull(message = "departure date cannot be empty")
    private Date departureDate;
    private TicketDto ticketDto;
}
