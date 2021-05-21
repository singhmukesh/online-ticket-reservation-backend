package com.cotiviti.onlineticketreservation.payment.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentRequestDto {
    private PaymentDto paymentMethod;
    private Long reservationId;
}
