package com.doodle.task.adapter.in.web.mapper;

import com.doodle.task.adapter.in.web.dto.CreateSlotRequest;
import com.doodle.task.adapter.in.web.dto.SlotResponse;
import com.doodle.task.domain.model.Slot;
import com.doodle.task.domain.port.in.CreateSlotCommand;
import org.springframework.stereotype.Component;

@Component
public class SlotWebMapper {

    public CreateSlotCommand toCommand(CreateSlotRequest request) {
        return new CreateSlotCommand(request.getUserId(), request.getStartTime(), request.getEndTime());
    }

    public SlotResponse toResponse(Slot slot) {
        return new SlotResponse(slot.id(), slot.userId(), slot.startTime(), slot.endTime(), slot.status());
    }
}
