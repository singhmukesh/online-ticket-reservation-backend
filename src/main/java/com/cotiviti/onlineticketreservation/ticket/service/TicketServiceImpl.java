package com.cotiviti.onlineticketreservation.ticket.service;

import com.cotiviti.onlineticketreservation.ticket.dto.TicketDto;
import com.cotiviti.onlineticketreservation.ticket.entity.Ticket;
import com.cotiviti.onlineticketreservation.ticket.mapper.TicketMapper;
import com.cotiviti.onlineticketreservation.ticket.repository.TicketRepository;
import com.cotiviti.onlineticketreservation.ticket.utils.TicketNumberGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(rollbackFor = {Exception.class}, readOnly = true)
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    @Transactional(readOnly = false)
    public TicketDto save(TicketDto ticketDto) {
        TicketDto savedTicket = new TicketDto();
        Ticket ticket = ticketRepository.save(TicketMapper.INSTANCE.toEntity(ticketDto));
        if (ticket != null) {
            savedTicket = TicketMapper.INSTANCE.toDto(ticket);
        }
        return savedTicket;
    }

    @Override
    @Transactional(readOnly = false)
    public TicketDto saveEventTickets(TicketDto ticketDto, Long eventId) {
        if (ticketDto != null) {
            List<TicketDto> tickets = TicketNumberGenerator.createTicketNumberAndPopulateDto(ticketDto);
            if (!tickets.isEmpty()) {
                tickets.forEach(ticket -> {
                    ticket.setEventId(eventId);
                    ticketRepository.save(TicketMapper.INSTANCE.toEntity(ticket));
                });
            }
        }
        return null;
    }

    @Override
    public TicketDto findByEventId(Long id) {
        return TicketMapper.INSTANCE.toDto(ticketRepository.findFirstByEventId(id));
    }
}
