package com.doodle.task.adapter.out.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("meetings")
@Getter
@AllArgsConstructor
public class MeetingJpaEntity {
    @Id
    private Long id;
    private Long slotId;
    private String title;
    private String description;
}
