package com.doodle.task.domain.port.out;

import com.doodle.task.domain.model.Slot;
import com.doodle.task.domain.model.SlotStatus;

import java.util.List;
import java.util.Optional;

public interface SlotRepository {

    Slot save(Slot slot);

    Optional<Slot> findById(Long id);

    void deleteById(Long id);

    List<Slot> findByUserIdAndStatus(Long userId, SlotStatus status);
}
