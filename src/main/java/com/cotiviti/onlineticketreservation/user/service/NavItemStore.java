package com.cotiviti.onlineticketreservation.user.service;

import com.cotiviti.onlineticketreservation.config.LoggedInUserDetail;
import com.cotiviti.onlineticketreservation.user.dto.NavItem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class NavItemStore {
    private final LoggedInUserDetail loggedInUserDetail;

    public NavItemStore(LoggedInUserDetail loggedInUserDetail) {
        this.loggedInUserDetail = loggedInUserDetail;
    }

    public List<NavItem> getAllNavItems() {
        List<NavItem> navItemsForAdmin = new ArrayList<>();
        List<NavItem> navItemsForUser = new ArrayList<>();
        navItemsForAdmin.add(new NavItem(1L, "Dashboard", "dashboard", "user/dashboard"));
        navItemsForAdmin.add(new NavItem(2L, "Events", "memory", "user/events"));
        navItemsForAdmin.add(new NavItem(3L, "Payment Method", "memory", "user/payment"));
        navItemsForAdmin.add(new NavItem(4L, "Reservation", "attach_email", "user/reservation"));

        navItemsForUser.add(new NavItem(1L, "Booking", "attach_email", "user/booking"));
        navItemsForUser.add(new NavItem(2L, "History", "memory", "user/history"));

        Authentication authentication = loggedInUserDetail.getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (StringUtils.equals(grantedAuthority.getAuthority(), "ROLE_ADMIN")) {
                return navItemsForAdmin;
            }
        }
        return navItemsForUser;
    }
}
