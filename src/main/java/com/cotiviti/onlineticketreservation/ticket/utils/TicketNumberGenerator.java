package com.cotiviti.onlineticketreservation.ticket.utils;

import com.cotiviti.onlineticketreservation.enums.TicketStatus;
import com.cotiviti.onlineticketreservation.ticket.dto.TicketDto;
import org.springframework.util.StringUtils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TicketNumberGenerator {
    private final static Integer CODE_LENGTH = 6;

    public static List<TicketDto> createTicketNumberAndPopulateDto(TicketDto ticketDto) {
        List<TicketDto> ticketDtoList = new ArrayList<>();
        for (int i = 0; i < ticketDto.getNumberOfTickets(); i++) {
            String ticketNumber = createTicketNumber();
            TicketDto ticket = buildTicketDto(ticketDto, StringUtils.capitalize(ticketNumber));
            ticketDtoList.add(ticket);
        }
        return ticketDtoList;
    }

    private static TicketDto buildTicketDto(TicketDto ticketDto, String ticketNumber) {
        TicketDto ticket = new TicketDto();
        ticket.setTicketNumber(ticketNumber);
        ticket.setTicketType(ticketDto.getTicketType());
        ticket.setCost(ticketDto.getCost());
        ticket.setTicketStatus(TicketStatus.AVAILABLE);
        return ticket;
    }

    public static String createTicketNumber() {
        char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        StringBuilder sb = new StringBuilder("FL");
        Random random = new SecureRandom();
        for (int i = 0; i < CODE_LENGTH; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        return output;
    }
}
