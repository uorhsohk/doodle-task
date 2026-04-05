package com.doodle.task.domain.port.in;

import com.doodle.task.domain.model.User;

public interface UserUseCase {
    User createUser(CreateUserCommand command);
}
