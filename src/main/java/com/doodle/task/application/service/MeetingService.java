package com.doodle.task.application.service;

import com.doodle.task.domain.model.Meeting;
import com.doodle.task.domain.model.MeetingParticipant;
import com.doodle.task.domain.model.ParticipantStatus;
import com.doodle.task.domain.port.in.MeetingUseCase;
import com.doodle.task.domain.port.in.ScheduleMeetingCommand;
import com.doodle.task.domain.port.in.UpdateParticipantStatusCommand;
import com.doodle.task.domain.port.out.MeetingRepository;
import com.doodle.task.domain.port.out.SlotRepository;
import com.doodle.task.domain.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MeetingService implements MeetingUseCase {

    private final MeetingRepository meetingRepository;
    private final SlotRepository slotRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Meeting scheduleMeeting(ScheduleMeetingCommand command) {
        var slot = slotRepository.findById(command.slotId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Slot not found: " + command.slotId()));

        if (!slot.isFree()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Slot is already booked: " + command.slotId());
        }

        for (Long userId : command.participantUserIds()) {
            userRepository.findById(userId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found: " + userId));
        }

        slotRepository.save(slot.markBusy());

        var participants = command.participantUserIds().stream()
                .map(userId -> new MeetingParticipant(userId, ParticipantStatus.TENTATIVE))
                .toList();

        var meeting = new Meeting(null, command.slotId(), command.title(), command.description(), participants);
        return meetingRepository.save(meeting);
    }

    @Override
    public Optional<Meeting> getMeeting(Long meetingId) {
        return meetingRepository.findById(meetingId);
    }

    @Override
    @Transactional
    public void updateParticipantStatus(UpdateParticipantStatusCommand command) {
        var meeting = meetingRepository.findById(command.meetingId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Meeting not found: " + command.meetingId()));

        boolean found = meeting.getParticipants().removeIf(p -> p.userId().equals(command.userId()));
        if (!found) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User " + command.userId() + " is not a participant of meeting " + command.meetingId());
        }

        meeting.getParticipants().add(new MeetingParticipant(command.userId(), command.status()));
        meetingRepository.save(meeting);
    }
}
