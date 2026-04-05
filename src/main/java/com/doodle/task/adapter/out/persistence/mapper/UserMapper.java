package com.doodle.task.adapter.out.persistence.mapper;

import com.doodle.task.domain.model.User;
import com.doodle.task.adapter.out.persistence.entity.UserJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toDomain(UserJpaEntity entity) {
        return new User(entity.getId(), entity.getEmail(), entity.getName());
    }

    public UserJpaEntity toJpa(User user) {
        return new UserJpaEntity(user.id(), user.email(), user.name());
    }
}
