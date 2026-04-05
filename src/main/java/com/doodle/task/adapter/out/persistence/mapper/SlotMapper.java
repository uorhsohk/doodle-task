package com.doodle.task.adapter.out.persistence.mapper;

import com.doodle.task.adapter.out.persistence.entity.SlotJpaEntity;
import com.doodle.task.domain.model.Slot;
import org.springframework.stereotype.Component;

@Component
public class SlotMapper {

    public Slot toDomain(SlotJpaEntity entity) {
        return new Slot(
                entity.getId(),
                entity.getUserId(),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getStatus()
        );
    }

    public SlotJpaEntity toJpa(Slot slot) {
        return new SlotJpaEntity(
                slot.id(),
                slot.userId(),
                slot.startTime(),
                slot.endTime(),
                slot.status()
        );
    }
}
