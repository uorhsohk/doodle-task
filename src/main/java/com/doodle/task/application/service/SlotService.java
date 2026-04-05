package com.doodle.task.application.service;

import com.doodle.task.domain.model.Slot;
import com.doodle.task.domain.model.SlotStatus;
import com.doodle.task.domain.port.in.CreateSlotCommand;
import com.doodle.task.domain.port.in.SlotUseCase;
import com.doodle.task.domain.port.out.SlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SlotService implements SlotUseCase {

    private final SlotRepository slotRepository;

    @Override
    @Transactional
    public Slot createSlot(CreateSlotCommand command) {
        if (!command.startTime().isBefore(command.endTime())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start time must be before end time");
        }

        return slotRepository.save(new Slot(null, command.userId(), command.startTime(), command.endTime(), SlotStatus.FREE));
    }

    @Override
    @Transactional
    public void deleteSlot(Long slotId) {
        slotRepository.findById(slotId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Slot not found: " + slotId));
        slotRepository.deleteById(slotId);
    }

    @Override
    public List<Slot> getSlotsByUser(Long userId, SlotStatus status) {
        return slotRepository.findByUserIdAndStatus(userId, status);
    }
}
