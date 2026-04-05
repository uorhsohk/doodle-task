package com.doodle.task.adapter.out.persistence.mapper;

import com.doodle.task.adapter.out.persistence.entity.MeetingJpaEntity;
import com.doodle.task.adapter.out.persistence.entity.MeetingParticipantJpaEntity;
import com.doodle.task.domain.model.Meeting;
import com.doodle.task.domain.model.MeetingParticipant;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MeetingMapper {

    public Meeting toDomain(MeetingJpaEntity entity, List<MeetingParticipantJpaEntity> participantEntities) {
        List<MeetingParticipant> participants = participantEntities.stream()
                .map(MeetingMapper::getMeetingParticipant)
                .toList();

        return new Meeting(
                entity.getId(),
                entity.getSlotId(),
                entity.getTitle(),
                entity.getDescription(),
                participants
        );
    }

    public MeetingJpaEntity toJpa(Meeting meeting) {
        return new MeetingJpaEntity(
                meeting.getId(),
                meeting.getSlotId(),
                meeting.getTitle(),
                meeting.getDescription()
        );
    }

    public MeetingParticipantJpaEntity toParticipantJpa(Long meetingId, MeetingParticipant participant) {
        return new MeetingParticipantJpaEntity(null, meetingId, participant.userId(), participant.status());
    }

    private static @NonNull MeetingParticipant getMeetingParticipant(MeetingParticipantJpaEntity p) {
        return new MeetingParticipant(p.getUserId(), p.getStatus());
    }
}
