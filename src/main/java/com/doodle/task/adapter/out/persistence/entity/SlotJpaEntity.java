package com.doodle.task.adapter.out.persistence.entity;

import com.doodle.task.domain.model.SlotStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("slots")
@Getter
@AllArgsConstructor
public class SlotJpaEntity {

    @Id
    private Long id;
    private Long userId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private SlotStatus status;
}
