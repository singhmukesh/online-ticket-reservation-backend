package com.cotiviti.onlineticketreservation.ticket.repository;

import com.cotiviti.onlineticketreservation.ticket.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Ticket findByEventId(Long eventId);

    Ticket findFirstByEventId(Long eventId);
}
