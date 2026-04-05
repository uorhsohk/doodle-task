package com.doodle.task.adapter.in.web.dto;

import com.doodle.task.domain.model.ParticipantStatus;

public record MeetingParticipantResponse(Long userId, ParticipantStatus status) {
}
