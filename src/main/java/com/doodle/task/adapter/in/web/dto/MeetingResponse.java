package com.doodle.task.adapter.in.web.dto;

import java.util.List;

public record MeetingResponse(
        Long id,
        Long slotId,
        String title,
        String description,
        List<MeetingParticipantResponse> participants
) {
}
