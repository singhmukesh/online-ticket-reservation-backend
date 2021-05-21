package com.cotiviti.onlineticketreservation.event.dto;

import lombok.Data;

import java.util.List;

@Data
public class EventPageDto {
    private Long total;
    private List<EventDto> eventDtos;
}
