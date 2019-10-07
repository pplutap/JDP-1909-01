package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.LoginDto;
import com.kodilla.ecommercee.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @PostMapping
    public UserDto createUser(@RequestBody UserDto user) {
        return user;
    }

    @PutMapping("/{id}")
    public void blockUser(@PathVariable Long id) {
    }

    @GetMapping
    public LoginDto loginUser(@RequestBody UserDto user) {
        return new LoginDto(Boolean.TRUE, 12345L);
    }

}
