package com.doodle.task.domain.port.out;

import com.doodle.task.domain.model.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(Long id);

    boolean existsByEmail(String email);
}
