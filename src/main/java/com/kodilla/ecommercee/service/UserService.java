package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.exception.NotFoundException;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(final Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User with id=" + id + " doesn't exist.")
        );
    }

}
