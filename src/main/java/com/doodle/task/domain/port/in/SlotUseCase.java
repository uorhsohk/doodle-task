package com.doodle.task.domain.port.in;

import com.doodle.task.domain.model.Slot;
import com.doodle.task.domain.model.SlotStatus;

import java.util.List;

public interface SlotUseCase {
    Slot createSlot(CreateSlotCommand command);

    void deleteSlot(Long slotId);

    List<Slot> getSlotsByUser(Long userId, SlotStatus status);
}
