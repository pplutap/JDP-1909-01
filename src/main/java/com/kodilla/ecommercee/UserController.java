package com.kodilla.ecommercee;

import com.kodilla.ecommercee.dto.LoginDto;
import com.kodilla.ecommercee.dto.UserDto;
import com.kodilla.ecommercee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping("/{id}")
    public void blockUser(@PathVariable Long id) {
        userService.blockUser(id);
    }

    @GetMapping
    public LoginDto loginUser(@RequestBody UserDto userDto) {
        return userService.loginUser(userDto);
    }

}
