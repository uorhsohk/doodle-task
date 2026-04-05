package com.doodle.task.adapter.out.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
@Getter
@AllArgsConstructor
public class UserJpaEntity {
    @Id
    private Long id;
    private String email;
    private String name;
}
