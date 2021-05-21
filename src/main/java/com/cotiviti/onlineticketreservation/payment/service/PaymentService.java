package com.cotiviti.onlineticketreservation.payment.service;

import com.cotiviti.onlineticketreservation.payment.dto.PaymentDto;
import com.cotiviti.onlineticketreservation.payment.dto.PaymentPageDto;
import com.cotiviti.onlineticketreservation.payment.dto.PaymentRequestDto;
import com.cotiviti.onlineticketreservation.user.dto.NavItem;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface PaymentService {
    PaymentDto save(PaymentDto paymentDto);

    PaymentPageDto findAll(PageRequest pageRequest);

    void delete(Long id);

    PaymentDto getById(Long id);

    List<PaymentDto> getAllPaymentMethod();

    PaymentRequestDto pay(PaymentRequestDto paymentDto);

}
