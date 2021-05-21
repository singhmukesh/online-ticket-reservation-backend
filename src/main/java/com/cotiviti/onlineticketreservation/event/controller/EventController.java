package com.cotiviti.onlineticketreservation.event.controller;

import com.cotiviti.onlineticketreservation.event.dto.EventDto;
import com.cotiviti.onlineticketreservation.event.dto.EventPageDto;
import com.cotiviti.onlineticketreservation.event.service.EventService;
import com.cotiviti.onlineticketreservation.util.FieldValidationService;
import com.cotiviti.onlineticketreservation.util.Response;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.cotiviti.onlineticketreservation.util.PaginationUtil.getPageRequest;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {
    private final EventService eventService;
    private final FieldValidationService fieldValidationService;

    public EventController(EventService eventService, FieldValidationService fieldValidationService) {
        this.eventService = eventService;
        this.fieldValidationService = fieldValidationService;
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody EventDto eventDto, BindingResult result) {
        ResponseEntity<?> errorMap = fieldValidationService.fieldValidation(result);
        if (errorMap != null) {
            return errorMap;
        }
        return new Response().success(eventService.save(eventDto));
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
        PageRequest pageRequest = getPageRequest(page, perPage, sortBy, order);
        EventPageDto eventsDto = eventService.findAll(pageRequest);
        return new ResponseEntity(eventsDto, HttpStatus.OK);
    }

}
