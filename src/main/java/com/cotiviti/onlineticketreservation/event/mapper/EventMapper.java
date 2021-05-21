package com.cotiviti.onlineticketreservation.event.mapper;

import com.cotiviti.onlineticketreservation.event.dto.EventDto;
import com.cotiviti.onlineticketreservation.event.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    @Mapping(source = "eventDto.departureDate", target = "departureDate", dateFormat = "yyyy-MM-dd hh:mm:ss")
    Event toEntity(EventDto eventDto);

    @Mapping(source = "departureDate", target = "departureDate", dateFormat = "yyyy-MM-dd hh:mm:ss")
    EventDto toDto(Event event);

    List<EventDto> toDto(List<Event> events);
}
