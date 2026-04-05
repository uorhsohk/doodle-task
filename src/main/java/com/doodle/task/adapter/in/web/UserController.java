package com.doodle.task.adapter.in.web;

import com.doodle.task.adapter.in.web.dto.CreateUserRequest;
import com.doodle.task.adapter.in.web.dto.UserResponse;
import com.doodle.task.adapter.in.web.mapper.UserWebMapper;
import com.doodle.task.domain.port.in.CreateUserCommand;
import com.doodle.task.domain.port.in.UserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserUseCase userUseCase;
    private final UserWebMapper userWebMapper;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        var createUserCommand = userWebMapper.toCommand(request);
        var user = userUseCase.createUser(createUserCommand);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userWebMapper.toResponse(user));
    }
}
