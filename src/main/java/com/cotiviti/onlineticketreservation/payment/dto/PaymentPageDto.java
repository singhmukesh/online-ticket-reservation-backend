package com.cotiviti.onlineticketreservation.payment.dto;

import com.cotiviti.onlineticketreservation.event.dto.EventDto;
import lombok.Data;

import java.util.List;

@Data
public class PaymentPageDto {
    private Long total;
    private List<PaymentDto> paymentDtos;
}
