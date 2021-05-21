package com.cotiviti.onlineticketreservation.ticket.controller;

import com.cotiviti.onlineticketreservation.payment.dto.PaymentDto;
import com.cotiviti.onlineticketreservation.ticket.dto.TicketDto;
import com.cotiviti.onlineticketreservation.ticket.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ticket")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<TicketDto> save(@RequestBody TicketDto ticketDto) {
        return new ResponseEntity(ticketService.save(ticketDto), HttpStatus.CREATED);
    }

}
