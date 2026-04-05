package com.doodle.task.adapter.out.persistence.repository;

import com.doodle.task.adapter.out.persistence.entity.UserJpaEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface UserJpaRepository extends ListCrudRepository<UserJpaEntity, Long> {
    boolean existsByEmail(String email);
}
