package com.cotiviti.onlineticketreservation.payment.mapper;

import com.cotiviti.onlineticketreservation.payment.dto.PaymentDto;
import com.cotiviti.onlineticketreservation.payment.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PaymentMapper {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    Payment toEntity(PaymentDto paymentDto);

    PaymentDto toDto(Payment payment);

    List<PaymentDto> toDto(List<Payment> payments);

}
