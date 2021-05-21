package com.cotiviti.onlineticketreservation.reservation.dto;

import com.cotiviti.onlineticketreservation.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequestDto {
    private Long id;
    private BigDecimal totalCost;
    @Enumerated(value = EnumType.STRING)
    private ReservationStatus reservationStatus;
    private Long eventId;
    private Long userId;
    private String email;
    private String name;
    private String phoneNumber;
    private Integer ticketQuantity;
    private Long paymentId;
}
