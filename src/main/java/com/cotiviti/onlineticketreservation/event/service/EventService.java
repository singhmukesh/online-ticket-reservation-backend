package com.cotiviti.onlineticketreservation.event.service;

import com.cotiviti.onlineticketreservation.event.dto.EventDto;
import com.cotiviti.onlineticketreservation.event.dto.EventPageDto;
import org.springframework.data.domain.PageRequest;

import java.util.Map;

public interface EventService {
    EventDto save(EventDto eventDto);

    EventPageDto findAll(PageRequest pageRequest);

    EventDto getEventInfoById(Long eventId);

    void update(EventDto eventDto);

    Map<String, Object> getTotalEvents();
}
