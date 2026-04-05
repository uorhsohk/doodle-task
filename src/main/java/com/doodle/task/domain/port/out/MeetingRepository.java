package com.doodle.task.domain.port.out;

import com.doodle.task.domain.model.Meeting;

import java.util.Optional;

public interface MeetingRepository {

    Meeting save(Meeting meeting);

    Optional<Meeting> findById(Long id);
}
