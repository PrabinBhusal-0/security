package com.prabin.SSP02.service;

import com.prabin.SSP02.entity.User;
import com.prabin.SSP02.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email :: "+email));

        return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
