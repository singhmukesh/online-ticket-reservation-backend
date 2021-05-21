package com.cotiviti.onlineticketreservation.ticket.entity;

import com.cotiviti.onlineticketreservation.enums.TicketStatus;
import com.cotiviti.onlineticketreservation.enums.TicketType;
import com.cotiviti.onlineticketreservation.event.entity.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue
    private Long id;
    private String ticketNumber;
    @Enumerated(value = EnumType.STRING)
    private TicketType ticketType;
    private BigDecimal cost;
    @Enumerated(value = EnumType.STRING)
    private TicketStatus ticketStatus;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
