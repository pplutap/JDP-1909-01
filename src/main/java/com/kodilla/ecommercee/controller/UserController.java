package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.LoginDto;
import com.kodilla.ecommercee.dto.UserDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/users")
@CrossOrigin("*")
public class UserController {

    @GetMapping
    public List<UserDto> getAllUsers() {
        return Arrays.asList(
                new UserDto(1L, "Piotr", 1, 25678L),
                new UserDto(2L, "Admin", 1, 84635L),
                new UserDto(3L, "User", 0, 97256L)
        );
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public UserDto createUser(@RequestBody UserDto user) {
        return user;
    }

    @PutMapping("/{id}")
    public void blockUser(@PathVariable Long id) {
    }

    @PutMapping("/{id}/session")
    public void createSessionKey(@PathVariable Long id) {
    }

    @GetMapping("/{id}")
    public LoginDto loginUser() {
        return new LoginDto(Boolean.TRUE, 12345L);
    }

}
