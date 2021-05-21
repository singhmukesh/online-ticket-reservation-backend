package com.cotiviti.onlineticketreservation.user.controller;

import com.cotiviti.onlineticketreservation.config.LoggedInUserDetail;
import com.cotiviti.onlineticketreservation.user.dto.NavItem;
import com.cotiviti.onlineticketreservation.user.dto.UserDto;
import com.cotiviti.onlineticketreservation.user.service.NavItemStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final LoggedInUserDetail loggedInUserDetail;
    private final NavItemStore navItemStore;

    public UserController(LoggedInUserDetail loggedInUserDetail, NavItemStore navItemStore) {
        this.loggedInUserDetail = loggedInUserDetail;
        this.navItemStore = navItemStore;
    }

    @GetMapping
    public ResponseEntity<UserDto> getLoggedInUserDetail() {
        return new ResponseEntity(loggedInUserDetail.getLoggedInUserDetails(), HttpStatus.OK);
    }
    @GetMapping("/nav-items")
    public ResponseEntity<List<NavItem>> navItems() {
        return new ResponseEntity(navItemStore.getAllNavItems(), HttpStatus.OK);
    }
    @GetMapping("/roles")
    public ResponseEntity<List<String>> getLoggedInUserRoles() {
        return new ResponseEntity(loggedInUserDetail.getLoggedInUserRoles(), HttpStatus.OK);
    }
}
