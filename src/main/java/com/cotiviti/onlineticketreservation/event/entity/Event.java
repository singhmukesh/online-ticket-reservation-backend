package com.cotiviti.onlineticketreservation.event.entity;

import com.cotiviti.onlineticketreservation.enums.EventType;
import com.cotiviti.onlineticketreservation.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue
    private Long id;
    private String fromDestination;
    private String toDestination;
    @Enumerated(value = EnumType.STRING)
    private EventType eventType;
    private Integer totalNumberOfTickets;
    private Integer totalAvailableTickets;
    private Date departureDate;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
