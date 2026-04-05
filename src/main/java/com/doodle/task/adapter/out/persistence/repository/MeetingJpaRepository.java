package com.doodle.task.adapter.out.persistence.repository;

import com.doodle.task.adapter.out.persistence.entity.MeetingJpaEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface MeetingJpaRepository extends ListCrudRepository<MeetingJpaEntity, Long> {
}
