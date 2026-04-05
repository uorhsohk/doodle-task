package com.doodle.task.adapter.in.web.mapper;

import com.doodle.task.adapter.in.web.dto.MeetingParticipantResponse;
import com.doodle.task.adapter.in.web.dto.MeetingResponse;
import com.doodle.task.adapter.in.web.dto.ScheduleMeetingRequest;
import com.doodle.task.adapter.in.web.dto.UpdateParticipantStatusRequest;
import com.doodle.task.domain.model.Meeting;
import com.doodle.task.domain.model.MeetingParticipant;
import com.doodle.task.domain.port.in.ScheduleMeetingCommand;
import com.doodle.task.domain.port.in.UpdateParticipantStatusCommand;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;

@Component
public class MeetingWebMapper {

    public ScheduleMeetingCommand toCommand(ScheduleMeetingRequest request) {
        return new ScheduleMeetingCommand(
                request.getSlotId(),
                request.getTitle(),
                request.getDescription(),
                request.getParticipantUserIds()
        );
    }

    public UpdateParticipantStatusCommand toCommand(Long meetingId, UpdateParticipantStatusRequest request) {
        return new UpdateParticipantStatusCommand(meetingId, request.getUserId(), request.getStatus());
    }

    public MeetingResponse toResponse(Meeting meeting) {
        var participants = meeting.getParticipants().stream()
                .map(MeetingWebMapper::getMeetingParticipantResponse)
                .toList();

        return new MeetingResponse(meeting.getId(), meeting.getSlotId(), meeting.getTitle(), meeting.getDescription(), participants);
    }

    private static @NonNull MeetingParticipantResponse getMeetingParticipantResponse(MeetingParticipant p) {
        return new MeetingParticipantResponse(p.userId(), p.status());
    }
}
