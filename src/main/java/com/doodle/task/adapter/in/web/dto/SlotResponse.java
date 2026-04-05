package com.doodle.task.adapter.in.web.dto;

import com.doodle.task.domain.model.SlotStatus;

import java.time.LocalDateTime;

public record SlotResponse(
        Long id,
        Long userId,
        LocalDateTime startTime,
        LocalDateTime endTime,
        SlotStatus status
) {
}
