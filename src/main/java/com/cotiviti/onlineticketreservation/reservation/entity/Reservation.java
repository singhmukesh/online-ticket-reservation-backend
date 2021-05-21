package com.cotiviti.onlineticketreservation.reservation.entity;

import com.cotiviti.onlineticketreservation.enums.ReservationStatus;
import com.cotiviti.onlineticketreservation.event.entity.Event;
import com.cotiviti.onlineticketreservation.payment.entity.Payment;
import com.cotiviti.onlineticketreservation.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;
    private BigDecimal totalCost;
    @Enumerated(value = EnumType.STRING)
    private ReservationStatus reservationStatus;
    @OneToOne(optional = true)
    @JoinColumn(name = "payment_id")
    private Payment payment;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    @NotBlank(message = "please enter your email")
    private String email;
    @NotBlank(message = "name is required")
    private String name;
    @NotNull(message = "phone number is required")
    private String phoneNumber;
    @NotNull(message = "ticket quantity is required")
    private Integer ticketQuantity;
    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private Date bookedDate;
}
