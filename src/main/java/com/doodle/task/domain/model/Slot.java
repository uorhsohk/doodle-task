package com.doodle.task.domain.model;

import java.time.LocalDateTime;

public record Slot(Long id, Long userId, LocalDateTime startTime, LocalDateTime endTime, SlotStatus status) {

    public boolean isFree() {
        return this.status == SlotStatus.FREE;
    }

    public Slot markBusy() {
        return new Slot(id, userId, startTime, endTime, SlotStatus.BUSY);
    }
}
