package com.doodle.task.adapter.out.persistence.repository;

import com.doodle.task.adapter.out.persistence.entity.MeetingParticipantJpaEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface MeetingParticipantJpaRepository extends ListCrudRepository<MeetingParticipantJpaEntity, Long> {

    List<MeetingParticipantJpaEntity> findByMeetingId(Long meetingId);

    void deleteByMeetingId(Long meetingId);
}
