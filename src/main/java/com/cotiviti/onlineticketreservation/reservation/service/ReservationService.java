package com.cotiviti.onlineticketreservation.reservation.service;

import com.cotiviti.onlineticketreservation.reservation.dto.BookingInfoDto;
import com.cotiviti.onlineticketreservation.reservation.dto.ReservationDto;
import com.cotiviti.onlineticketreservation.reservation.dto.ReservationPageDto;
import com.cotiviti.onlineticketreservation.reservation.dto.ReservationRequestDto;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;

public interface ReservationService {
    ReservationDto save(ReservationDto reservationDto);

    BookingInfoDto getBookingInfoById(Long id);

    ReservationRequestDto getById(Long id);

    ReservationRequestDto updateReservationStatus(ReservationRequestDto reservationRequestDto);

    ReservationPageDto getBookingInfoByUserId(PageRequest pageRequest);

    ReservationPageDto findAll(PageRequest pageRequest);

    List<Map> getMonthlyBooking();

    List<Map> getMonthlyRevenue();

    Map<String,Object> metricsForDashboard();
}
