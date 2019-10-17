package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.exception.NotFoundException;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByName(final String userName) {
        if (userName == null) {
            return null;
        }
        return userRepository.findByUserName(userName).orElseThrow(
                () -> new NotFoundException("User with username='" + userName + "' doesn't exist.")
        );
    }

}
