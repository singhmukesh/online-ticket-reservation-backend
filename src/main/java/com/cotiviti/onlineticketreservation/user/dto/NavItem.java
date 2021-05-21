package com.cotiviti.onlineticketreservation.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NavItem {
    private Long id;
    private String displayName;
    private String iconName;
    private String route;
}
