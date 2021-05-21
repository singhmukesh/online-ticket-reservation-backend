package com.cotiviti.onlineticketreservation.user.mapper;

import com.cotiviti.onlineticketreservation.user.dto.UserDto;
import com.cotiviti.onlineticketreservation.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserDto userDto);

    UserDto toDto(User user);

}
