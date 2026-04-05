package com.doodle.task.adapter.out.persistence.repository;

import com.doodle.task.adapter.out.persistence.entity.SlotJpaEntity;
import com.doodle.task.domain.model.SlotStatus;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface SlotJpaRepository extends ListCrudRepository<SlotJpaEntity, Long> {
    List<SlotJpaEntity> findByUserIdAndStatus(Long userId, SlotStatus status);
}
