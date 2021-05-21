package com.cotiviti.onlineticketreservation.reservation.service;

import com.cotiviti.onlineticketreservation.config.LoggedInUserDetail;
import com.cotiviti.onlineticketreservation.enums.ReservationStatus;
import com.cotiviti.onlineticketreservation.event.dto.EventDto;
import com.cotiviti.onlineticketreservation.event.service.EventService;
import com.cotiviti.onlineticketreservation.reservation.dto.BookingInfoDto;
import com.cotiviti.onlineticketreservation.reservation.dto.ReservationDto;
import com.cotiviti.onlineticketreservation.reservation.dto.ReservationPageDto;
import com.cotiviti.onlineticketreservation.reservation.dto.ReservationRequestDto;
import com.cotiviti.onlineticketreservation.reservation.entity.Reservation;
import com.cotiviti.onlineticketreservation.reservation.mapper.ReservationMapper;
import com.cotiviti.onlineticketreservation.reservation.mapper.ReservationRequestMapper;
import com.cotiviti.onlineticketreservation.reservation.repository.ReservationRepository;
import com.cotiviti.onlineticketreservation.user.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final LoggedInUserDetail loggedInUserDetail;
    private final EventService eventService;

    public ReservationServiceImpl(ReservationRepository reservationRepository, LoggedInUserDetail loggedInUserDetail, EventService eventService) {
        this.reservationRepository = reservationRepository;
        this.loggedInUserDetail = loggedInUserDetail;
        this.eventService = eventService;
    }

    @Override
    public ReservationDto save(ReservationDto reservationDto) {
        reservationDto.setReservationStatus(ReservationStatus.PENDING);
        reservationDto.setUserId(loggedInUserDetail.getLoggedInUserDetails().getId());
        Reservation reservation = reservationRepository.save(ReservationMapper.INSTANCE.toEntity(reservationDto));
        return ReservationMapper.INSTANCE.toDto(reservation);
    }

    @Override
    public BookingInfoDto getBookingInfoById(Long id) {
        BookingInfoDto bookingInfoDto = new BookingInfoDto();
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            bookingInfoDto = populateEventInfo(reservation);
        }
        return bookingInfoDto;
    }

    private BookingInfoDto populateEventInfo(Reservation reservation) {
        BookingInfoDto bookingInfoDto = new BookingInfoDto();
        ReservationDto reservationDto = ReservationMapper.INSTANCE.toDto(reservation);
        EventDto eventDto = eventService.getEventInfoById(reservationDto.getEventId());
        bookingInfoDto.setEventDto(eventDto);
        bookingInfoDto.setReservationDto(reservationDto);
        return bookingInfoDto;
    }

    @Override
    public ReservationRequestDto getById(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isPresent()) {
            return ReservationRequestMapper.INSTANCE.toDto(reservation.get());
        }
        return null;
    }

    @Override
    public ReservationRequestDto updateReservationStatus(ReservationRequestDto reservationRequestDto) {
        Reservation reservation = reservationRepository.save(ReservationRequestMapper.INSTANCE.toEntity(reservationRequestDto));
        return ReservationRequestMapper.INSTANCE.toDto(reservation);
    }

    @Override
    public ReservationPageDto getBookingInfoByUserId(PageRequest pageRequest) {
        List<BookingInfoDto> bookingInfoDtos = new ArrayList<>();
        UserDto userDto = loggedInUserDetail.getLoggedInUserDetails();
        List<Reservation> reservations = reservationRepository.findAllByUserId(userDto.getId(), pageRequest);
        if (!reservations.isEmpty()) {
            reservations.forEach(reservation -> {
                BookingInfoDto bookingInfoDto = populateEventInfo(reservation);
                bookingInfoDtos.add(bookingInfoDto);
            });
        }
        ReservationPageDto reservationPageDto = new ReservationPageDto();
        reservationPageDto.setTotal((long) reservations.size());
        reservationPageDto.setBookingInfoDtos(bookingInfoDtos);
        return reservationPageDto;
    }

    @Override
    public ReservationPageDto findAll(PageRequest pageRequest) {
        List<BookingInfoDto> bookingInfoDtoList = new ArrayList<>();
        Page<Reservation> reservationPage = reservationRepository.findAll(pageRequest);
        List<Reservation> reservations = reservationPage.toList();
        if (reservations != null) {
            for (Reservation reservation : reservations) {
                BookingInfoDto bookingInfoDto = populateEventInfo(reservation);
                bookingInfoDtoList.add(bookingInfoDto);
            }
        }
        ReservationPageDto reservationPageDto = new ReservationPageDto();
        reservationPageDto.setTotal(reservationPage.getTotalElements());
        reservationPageDto.setBookingInfoDtos(bookingInfoDtoList);
        return reservationPageDto;
    }

    @Override
    public List<Map> getMonthlyBooking() {
        return reservationRepository.getBookingReport();
    }

    @Override
    public List<Map> getMonthlyRevenue() {
        return reservationRepository.getMonthlyRevenueReport();
    }

    @Override
    public Map<String, Object> metricsForDashboard() {
        Map<String, Object> data = new HashMap<>();
        data.putAll(reservationRepository.getSuccessfulReservation());
        data.putAll(reservationRepository.getTotalRevenue());
        data.putAll(eventService.getTotalEvents());
        return data;
    }
}
