package com.doodle.task.domain.port.in;

import java.time.LocalDateTime;

public record CreateSlotCommand(Long userId, LocalDateTime startTime, LocalDateTime endTime) {
}
