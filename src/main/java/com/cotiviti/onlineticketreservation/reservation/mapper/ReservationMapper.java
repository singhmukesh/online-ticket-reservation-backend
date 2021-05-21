package com.cotiviti.onlineticketreservation.reservation.mapper;

import com.cotiviti.onlineticketreservation.reservation.dto.ReservationDto;
import com.cotiviti.onlineticketreservation.reservation.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReservationMapper {
    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "eventId", target = "event.id")
    Reservation toEntity(ReservationDto reservationDto);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "event.id", target = "eventId")
    ReservationDto toDto(Reservation reservation);
}
