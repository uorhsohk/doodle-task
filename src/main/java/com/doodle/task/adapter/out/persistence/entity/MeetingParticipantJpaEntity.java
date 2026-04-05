package com.doodle.task.adapter.out.persistence.entity;

import com.doodle.task.domain.model.ParticipantStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("meeting_participants")
@Getter
@AllArgsConstructor
public class MeetingParticipantJpaEntity {

    @Id
    private Long id;
    private Long meetingId;
    private Long userId;
    private ParticipantStatus status;
}
