package com.doodle.task.adapter.out.persistence;

import com.doodle.task.adapter.out.persistence.entity.MeetingJpaEntity;
import com.doodle.task.adapter.out.persistence.mapper.MeetingMapper;
import com.doodle.task.adapter.out.persistence.repository.MeetingJpaRepository;
import com.doodle.task.adapter.out.persistence.repository.MeetingParticipantJpaRepository;
import com.doodle.task.domain.model.Meeting;
import com.doodle.task.domain.port.out.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MeetingPersistenceAdapter implements MeetingRepository {

    private final MeetingMapper meetingMapper;
    private final MeetingJpaRepository meetingJpaRepository;
    private final MeetingParticipantJpaRepository meetingParticipantJpaRepository;


    @Override
    public Meeting save(Meeting meeting) {
        var meetingEntity = meetingMapper.toJpa(meeting);
        var savedMeeting = meetingJpaRepository.save(meetingEntity);

        meetingParticipantJpaRepository.deleteByMeetingId(savedMeeting.getId());

        var participantEntities = meeting.getParticipants().stream()
                .map(p -> meetingMapper.toParticipantJpa(savedMeeting.getId(), p))
                .toList();
        meetingParticipantJpaRepository.saveAll(participantEntities);

        return getMeeting(savedMeeting);
    }

    @Override
    public Optional<Meeting> findById(Long id) {
        return meetingJpaRepository.findById(id).map(this::getMeeting);
    }

    private Meeting getMeeting(MeetingJpaEntity entity) {
        var participants = meetingParticipantJpaRepository.findByMeetingId(entity.getId());
        return meetingMapper.toDomain(entity, participants);
    }
}
