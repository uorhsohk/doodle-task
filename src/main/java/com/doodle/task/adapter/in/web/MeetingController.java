package com.doodle.task.adapter.in.web;

import com.doodle.task.adapter.in.web.dto.MeetingResponse;
import com.doodle.task.adapter.in.web.dto.ScheduleMeetingRequest;
import com.doodle.task.adapter.in.web.dto.UpdateParticipantStatusRequest;
import com.doodle.task.adapter.in.web.mapper.MeetingWebMapper;
import com.doodle.task.domain.port.in.MeetingUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/meetings")
@RequiredArgsConstructor
public class MeetingController {

    private final MeetingUseCase meetingUseCase;
    private final MeetingWebMapper meetingWebMapper;

    @PostMapping
    public ResponseEntity<MeetingResponse> scheduleMeeting(@Valid @RequestBody ScheduleMeetingRequest request) {
        var meeting = meetingUseCase.scheduleMeeting(meetingWebMapper.toCommand(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(meetingWebMapper.toResponse(meeting));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeetingResponse> getMeeting(@PathVariable Long id) {
        return meetingUseCase.getMeeting(id)
                .map(m -> ResponseEntity.ok(meetingWebMapper.toResponse(m)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/participants")
    public ResponseEntity<Void> updateParticipantStatus(@PathVariable Long id, @Valid @RequestBody UpdateParticipantStatusRequest request) {
        meetingUseCase.updateParticipantStatus(meetingWebMapper.toCommand(id, request));
        return ResponseEntity.noContent().build();
    }
}
