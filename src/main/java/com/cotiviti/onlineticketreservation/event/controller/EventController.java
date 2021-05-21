package com.cotiviti.onlineticketreservation.event.controller;

import com.cotiviti.onlineticketreservation.event.dto.EventDto;
import com.cotiviti.onlineticketreservation.event.dto.EventPageDto;
import com.cotiviti.onlineticketreservation.event.service.EventService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@RequestMapping("/api/v1/event")
@EnableSwagger2
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<EventDto> save(@RequestBody EventDto eventDto) {
        return new ResponseEntity(eventService.save(eventDto), HttpStatus.CREATED);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventDto> getEventInfo(@PathVariable("eventId") Long eventId) {
        return new ResponseEntity(eventService.getEventInfoById(eventId), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<EventPageDto> getEvents(@RequestParam(value = "page", required = false) Integer page,
                                                  @RequestParam(value = "per-page", required = false) Integer perPage,
                                                  @RequestParam(value = "sort-by", required = false) String sortBy,
                                                  @RequestParam(value = "order", required = false) String order) {
        PageRequest pageRequest;
        if (page != null && perPage != null && !sortBy.isBlank() && order.equals("desc")) {
            pageRequest = PageRequest.of(page, perPage, Sort.by(sortBy).descending());
        } else if (page != null && perPage != null && !sortBy.isBlank() && order.equals("asc")) {
            pageRequest = PageRequest.of(page, perPage, Sort.by(sortBy).ascending());
        } else if (page != null && perPage != null) {
            pageRequest = PageRequest.of(page, perPage, Sort.by("id").ascending());
        } else {
            pageRequest = PageRequest.of(0, 5, Sort.by("id").ascending());
        }
        EventPageDto eventsDto = eventService.findAll(pageRequest);
        return new ResponseEntity(eventsDto, HttpStatus.OK);
    }

}
