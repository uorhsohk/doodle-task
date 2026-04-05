package com.doodle.task.adapter.in.web.mapper;

import com.doodle.task.adapter.in.web.dto.CreateUserRequest;
import com.doodle.task.adapter.in.web.dto.UserResponse;
import com.doodle.task.domain.model.User;
import com.doodle.task.domain.port.in.CreateUserCommand;
import org.springframework.stereotype.Component;

@Component
public class UserWebMapper {

    public CreateUserCommand toCommand(CreateUserRequest request) {
        return new CreateUserCommand(request.getEmail(), request.getName());
    }

    public UserResponse toResponse(User user) {
        return new UserResponse(user.id(), user.email(), user.name());
    }
}
