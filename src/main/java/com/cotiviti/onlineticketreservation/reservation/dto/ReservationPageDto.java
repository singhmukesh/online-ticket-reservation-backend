package com.cotiviti.onlineticketreservation.reservation.dto;

import lombok.Data;

import java.util.List;
@Data
public class ReservationPageDto {
    private Long total;
    private List<BookingInfoDto> bookingInfoDtos;
}
