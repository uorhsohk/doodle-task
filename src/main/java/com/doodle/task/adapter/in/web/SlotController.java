package com.doodle.task.adapter.in.web;

import com.doodle.task.adapter.in.web.dto.CreateSlotRequest;
import com.doodle.task.adapter.in.web.dto.SlotResponse;
import com.doodle.task.adapter.in.web.mapper.SlotWebMapper;
import com.doodle.task.domain.model.SlotStatus;
import com.doodle.task.domain.port.in.SlotUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/slots")
@RequiredArgsConstructor
public class SlotController {

    private final SlotUseCase slotUseCase;
    private final SlotWebMapper slotWebMapper;

    @PostMapping
    public ResponseEntity<SlotResponse> createSlot(@Valid @RequestBody CreateSlotRequest request) {
        var slot = slotUseCase.createSlot(slotWebMapper.toCommand(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(slotWebMapper.toResponse(slot));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SlotResponse>> getSlotsByUser(@PathVariable Long userId,
                                                              @RequestParam(required = false) SlotStatus status) {
        return ResponseEntity.ok(slotUseCase.getSlotsByUser(userId, status).stream()
                .map(slotWebMapper::toResponse)
                .toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSlot(@PathVariable Long id) {
        slotUseCase.deleteSlot(id);
        return ResponseEntity.noContent().build();
    }
}
