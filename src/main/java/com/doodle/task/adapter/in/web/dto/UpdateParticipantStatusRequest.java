package com.doodle.task.adapter.in.web.dto;

import com.doodle.task.domain.model.ParticipantStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateParticipantStatusRequest {
    @NotNull
    private Long userId;
    @NotNull
    private ParticipantStatus status;
}
