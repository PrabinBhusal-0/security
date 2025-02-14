package com.prabin.SSP02.controller;

import com.prabin.SSP02.entity.User;
import com.prabin.SSP02.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepo userRepo;

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/admin")
    public String adminMethod(){
        return "i am admin";
    }

    @PreAuthorize("hasAnyRole('admin','user')")
    @GetMapping("/user")
    public String userMethod(){
        return "you are just a user";
    }

    @GetMapping("/db")
    public Optional<User> userDb(){
        return userRepo.findByEmail("prabinb761@gmail.com");
    }
}
