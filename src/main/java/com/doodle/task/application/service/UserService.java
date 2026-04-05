package com.doodle.task.application.service;

import com.doodle.task.domain.model.User;
import com.doodle.task.domain.port.in.CreateUserCommand;
import com.doodle.task.domain.port.in.UserUseCase;
import com.doodle.task.domain.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService implements UserUseCase {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User createUser(CreateUserCommand command) {
        if (userRepository.existsByEmail(command.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use: " + command.email());
        }

        return userRepository.save(new User(null, command.email(), command.name()));
    }
}
