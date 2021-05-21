package com.cotiviti.onlineticketreservation.payment.service;

import com.cotiviti.onlineticketreservation.enums.ReservationStatus;
import com.cotiviti.onlineticketreservation.event.dto.EventDto;
import com.cotiviti.onlineticketreservation.event.service.EventService;
import com.cotiviti.onlineticketreservation.payment.dto.PaymentDto;
import com.cotiviti.onlineticketreservation.payment.dto.PaymentPageDto;
import com.cotiviti.onlineticketreservation.payment.dto.PaymentRequestDto;
import com.cotiviti.onlineticketreservation.payment.entity.Payment;
import com.cotiviti.onlineticketreservation.payment.mapper.PaymentMapper;
import com.cotiviti.onlineticketreservation.payment.repository.PaymentRepository;
import com.cotiviti.onlineticketreservation.reservation.dto.ReservationRequestDto;
import com.cotiviti.onlineticketreservation.reservation.service.ReservationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final ReservationService reservationService;
    private final EventService eventService;

    public PaymentServiceImpl(PaymentRepository paymentRepository, ReservationService reservationService, EventService eventService) {
        this.paymentRepository = paymentRepository;
        this.reservationService = reservationService;
        this.eventService = eventService;
    }

    @Override
    public PaymentDto save(PaymentDto paymentDto) {
        PaymentDto returnValue = new PaymentDto();
        Payment payment = paymentRepository.save(PaymentMapper.INSTANCE.toEntity(paymentDto));
        if (payment != null) {
            returnValue = PaymentMapper.INSTANCE.toDto(payment);
        }
        return returnValue;
    }

    @Override
    public PaymentPageDto findAll(PageRequest pageRequest) {
        Page<Payment> paymentPage = paymentRepository.findAll(pageRequest);
        List<PaymentDto> paymentDto = PaymentMapper.INSTANCE.toDto(paymentPage.toList());
        PaymentPageDto paymentPageDto = new PaymentPageDto();
        paymentPageDto.setTotal(paymentPage.getTotalElements());
        paymentPageDto.setPaymentDtos(paymentDto);
        return paymentPageDto;
    }

    @Override
    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public PaymentDto getById(Long id) {
        PaymentDto paymentDto = new PaymentDto();
        Payment payment = paymentRepository.findById(id).get();
        if (payment != null) {
            paymentDto = PaymentMapper.INSTANCE.toDto(payment);
        }
        return paymentDto;
    }

    @Override
    public List<PaymentDto> getAllPaymentMethod() {
        List<PaymentDto> paymentDtos = new ArrayList<>();
        List<Payment> payments = paymentRepository.findAll();
        if (payments != null) {
            paymentDtos = PaymentMapper.INSTANCE.toDto(payments);
        }
        return paymentDtos;
    }

    @Override
    public PaymentRequestDto pay(PaymentRequestDto paymentDto) {
        ReservationRequestDto reservation = reservationService.getById(paymentDto.getReservationId());
        reservation.setPaymentId(paymentDto.getPaymentMethod().getId());
        reservation.setReservationStatus(ReservationStatus.SUCCESS);
        reservationService.updateReservationStatus(reservation);
        EventDto eventDto = eventService.getEventInfoById(reservation.getEventId());
        eventDto.setTotalAvailableTickets(eventDto.getTotalNumberOfTickets() - reservation.getTicketQuantity());
        eventService.update(eventDto);
        return paymentDto;
    }

}
