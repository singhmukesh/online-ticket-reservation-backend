package com.cotiviti.onlineticketreservation.config;

import com.cotiviti.onlineticketreservation.user.dto.UserDto;
import com.cotiviti.onlineticketreservation.user.entity.User;
import com.cotiviti.onlineticketreservation.user.mapper.UserMapper;
import com.cotiviti.onlineticketreservation.user.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class LoggedInUserDetail {
    private final UserRepository userRepository;

    public LoggedInUserDetail(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Authentication getAuthentication() {
        Authentication loggedInUserDetail = SecurityContextHolder.getContext().getAuthentication();
        Authentication authentication = ((OAuth2Authentication) loggedInUserDetail).getUserAuthentication();
        return authentication;
    }

    public UserDto getLoggedInUserDetails() {
        Authentication authentication = getAuthentication();
        Map<String, String> details = (Map<String, String>) authentication.getDetails();
        Optional<User> optionalUser = userRepository.findByEmail(details.get("username"));
        optionalUser.orElseThrow(() -> new UsernameNotFoundException("User with given email does not exist"));
        User user = optionalUser.get();
        UserDto userDto = UserMapper.INSTANCE.toDto(user);
        return userDto;
    }

    public List<String> getLoggedInUserRoles() {
        List<String> roleList = new ArrayList<>();
        Authentication authentication = getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        authorities.forEach(grantedAuthority -> {
            roleList.add(grantedAuthority.getAuthority());
        });
        return roleList;
    }
}
