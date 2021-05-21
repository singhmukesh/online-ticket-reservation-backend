package com.cotiviti.onlineticketreservation.event.service;

import com.cotiviti.onlineticketreservation.event.dto.EventDto;
import com.cotiviti.onlineticketreservation.event.dto.EventPageDto;
import com.cotiviti.onlineticketreservation.event.entity.Event;
import com.cotiviti.onlineticketreservation.event.mapper.EventMapper;
import com.cotiviti.onlineticketreservation.event.repository.EventRepository;
import com.cotiviti.onlineticketreservation.ticket.dto.TicketDto;
import com.cotiviti.onlineticketreservation.ticket.service.TicketService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private static final Integer MINIMUM_TICKET_COUNT = 1;
    private final EventRepository eventRepository;
    private final TicketService ticketService;

    public EventServiceImpl(EventRepository eventRepository, TicketService ticketService) {
        this.eventRepository = eventRepository;
        this.ticketService = ticketService;
    }

    @Override
    public EventDto save(EventDto eventDto) {
        EventDto saveEvent = new EventDto();
        Event event = eventRepository.save(EventMapper.INSTANCE.toEntity(eventDto));
        if (event != null) {
            saveEvent = EventMapper.INSTANCE.toDto(event);
        }
        ticketService.saveEventTickets(eventDto.getTicketDto(), saveEvent.getId());
        return saveEvent;
    }

    @Override
    public EventPageDto findAll(PageRequest pageRequest) {
        List<EventDto> eventDtoList = new ArrayList<>();
        Page<Event> eventPage = eventRepository.findAllByTotalAvailableTicketsGreaterThanEqual(MINIMUM_TICKET_COUNT, pageRequest);
        List<EventDto> eventDtos = EventMapper.INSTANCE.toDto(eventPage.toList());
        if (eventDtos != null) {
            for (EventDto eventDto : eventDtos) {
                TicketDto ticketDto = ticketService.findByEventId(eventDto.getId());
                eventDto.setTicketDto(ticketDto);
                eventDtoList.add(eventDto);
            }
        }
        EventPageDto paymentPageDto = new EventPageDto();
        paymentPageDto.setTotal(eventPage.getTotalElements());
        paymentPageDto.setEventDtos(eventDtos);
        return paymentPageDto;
    }

    @Override
    public EventDto getEventInfoById(Long eventId) {
        EventDto eventDto = new EventDto();
        Optional<Event> event = eventRepository.findById(eventId);
        if (event.isPresent()) {
            eventDto = EventMapper.INSTANCE.toDto(event.get());
            TicketDto ticketDto = ticketService.findByEventId(eventDto.getId());
            eventDto.setTicketDto(ticketDto);
        }
        return eventDto;
    }

    @Override
    public void update(EventDto eventDto) {
        eventRepository.save(EventMapper.INSTANCE.toEntity(eventDto));
    }

    @Override
    public Map<String, Object> getTotalEvents() {
        return eventRepository.getTotalRevenue();
    }
}
