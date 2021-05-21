package com.cotiviti.onlineticketreservation.reservation.mapper;

import com.cotiviti.onlineticketreservation.reservation.dto.ReservationRequestDto;
import com.cotiviti.onlineticketreservation.reservation.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReservationRequestMapper {
    ReservationRequestMapper INSTANCE = Mappers.getMapper(ReservationRequestMapper.class);

    @Mapping(source = "paymentId", target = "payment.id")
    @Mapping(source = "eventId", target = "event.id")
    @Mapping(source = "userId", target = "user.id")
    Reservation toEntity(ReservationRequestDto reservationDto);

    @Mapping(source = "payment.id", target = "paymentId")
    @Mapping(source = "event.id", target = "eventId")
    @Mapping(source = "user.id", target = "userId")
    ReservationRequestDto toDto(Reservation reservation);
}
