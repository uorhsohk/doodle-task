package com.doodle.task.domain.port.in;

import java.util.List;

public record ScheduleMeetingCommand(Long slotId, String title, String description, List<Long> participantUserIds) {
}
