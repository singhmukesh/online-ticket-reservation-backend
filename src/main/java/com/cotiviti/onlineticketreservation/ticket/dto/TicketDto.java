package com.cotiviti.onlineticketreservation.ticket.dto;

import com.cotiviti.onlineticketreservation.enums.TicketStatus;
import com.cotiviti.onlineticketreservation.enums.TicketType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {
    private Long id;
    private String ticketNumber;
    @Enumerated(value = EnumType.STRING)
    private TicketType ticketType;
    private BigDecimal cost;
    @Enumerated(value = EnumType.STRING)
    private TicketStatus ticketStatus;
    private Long eventId;
    private Integer numberOfTickets;
}
