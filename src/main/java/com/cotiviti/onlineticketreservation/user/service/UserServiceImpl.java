package com.cotiviti.onlineticketreservation.user.service;

import com.cotiviti.onlineticketreservation.user.entity.AuthUser;
import com.cotiviti.onlineticketreservation.user.entity.User;
import com.cotiviti.onlineticketreservation.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email %S does not exist", email)));
        UserDetails userDetails = new AuthUser(user);
        return userDetails;
    }
}
