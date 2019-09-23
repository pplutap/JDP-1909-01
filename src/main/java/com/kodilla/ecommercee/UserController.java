package com.kodilla.ecommercee;

import com.kodilla.ecommercee.dto.LoginDto;
import com.kodilla.ecommercee.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    public final static Integer BLOCKED = 0;
    public final static Integer ACTIVE = 1;

    private List<UserDto> sampleUsers = new ArrayList<>(
            Arrays.asList(
                    new UserDto(1L, "Piotr", ACTIVE, 59403L),
                    new UserDto(2L, "Admin", ACTIVE, 96997L),
                    new UserDto(3L, "User", BLOCKED, 31251L)
            )
    );

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        if (sampleUsers.stream().anyMatch(user -> user.getUsername().equals(userDto.getUsername()))) {
            throw new RuntimeException("Username already taken.");
        }
        long maxId = sampleUsers.stream()
                .map(UserDto::getId)
                .max(Long::compareTo)
                .orElse(0L);
        userDto.setId(maxId + 1);
        return userDto;
    }

    @PutMapping("/{id}")
    public void blockUser(@PathVariable Long id) {
        UserDto userDto = sampleUsers.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found."));
        userDto.setStatus(BLOCKED);
    }

    @GetMapping
    public LoginDto loginUser(@RequestBody UserDto userDto) {
        if (sampleUsers.stream().anyMatch(user -> user.getUsername().equals(userDto.getUsername()) &&
                user.getUserKey().equals(userDto.getUserKey()) &&
                user.getStatus().equals(ACTIVE))) {
            return new LoginDto(Boolean.TRUE, 12345L);
        }
        return new LoginDto(Boolean.FALSE, null);
    }

}
