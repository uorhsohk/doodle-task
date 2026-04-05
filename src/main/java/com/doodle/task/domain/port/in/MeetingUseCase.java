package com.doodle.task.domain.port.in;

import com.doodle.task.domain.model.Meeting;

import java.util.Optional;

public interface MeetingUseCase {
    Meeting scheduleMeeting(ScheduleMeetingCommand command);

    void updateParticipantStatus(UpdateParticipantStatusCommand command);

    Optional<Meeting> getMeeting(Long meetingId);
}
