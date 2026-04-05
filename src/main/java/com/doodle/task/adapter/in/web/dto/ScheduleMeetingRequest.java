package com.doodle.task.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleMeetingRequest {
    @NotNull
    private Long slotId;
    @NotBlank
    private String title;
    private String description;
    @NotNull
    private List<Long> participantUserIds;
}
