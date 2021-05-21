package com.cotiviti.onlineticketreservation.user.dto;

import lombok.Data;


@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String phoneNumber;
}
