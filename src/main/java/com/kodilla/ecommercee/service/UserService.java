package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dto.LoginDto;
import com.kodilla.ecommercee.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    public final static Integer BLOCKED = 0;
    public final static Integer ACTIVE = 1;

    private List<UserDto> sampleUsers = new ArrayList<>(
            Arrays.asList(
                    new UserDto(1L, "Piotr", ACTIVE, 59403L),
                    new UserDto(2L, "Admin", ACTIVE, 96997L),
                    new UserDto(3L, "User", BLOCKED, 31251L)
            )
    );

    public UserDto createUser(final UserDto userDto) {
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

    public void blockUser(final Long id) {
        UserDto userDto = sampleUsers.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found."));
        userDto.setStatus(BLOCKED);
    }

    public LoginDto loginUser(final UserDto userDto) {
        if (sampleUsers.stream().anyMatch(user -> user.getUsername().equals(userDto.getUsername()) &&
                user.getUserKey().equals(userDto.getUserKey()) &&
                user.getStatus().equals(ACTIVE))) {
            return new LoginDto(Boolean.TRUE, 12345L);
        }
        return new LoginDto(Boolean.FALSE, null);
    }

}
