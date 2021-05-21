package com.cotiviti.onlineticketreservation.reservation.dto;

import com.cotiviti.onlineticketreservation.event.dto.EventDto;
import lombok.Data;

@Data
public class BookingInfoDto {
    private ReservationDto reservationDto;
    private EventDto eventDto;
}
