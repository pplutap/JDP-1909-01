package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.CredentialsDto;
import com.kodilla.ecommercee.dto.LoginDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/login")
@CrossOrigin("*")
public class LoginController {

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public LoginDto loginUser(@RequestBody CredentialsDto credentialsDto) {
        return new LoginDto(Boolean.TRUE, 12345L);
    }

}
