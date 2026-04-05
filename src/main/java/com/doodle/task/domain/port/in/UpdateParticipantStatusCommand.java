package com.doodle.task.domain.port.in;

import com.doodle.task.domain.model.ParticipantStatus;

public record UpdateParticipantStatusCommand(Long meetingId, Long userId, ParticipantStatus status) {
}
