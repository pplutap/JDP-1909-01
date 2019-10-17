package com.kodilla.ecommercee.controller;

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
                new UserDto(1L, "Piotr", "Piotr123", 1, 25678L, "bbbbbbb@bbbbb.com"),
                new UserDto(2L, "Admin", "admin666", 1, 84635L, "a@a.a"),
                new UserDto(3L, "User", "user963", 0, 97256L, "ddddddd@ddddd.pl")
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

}
