package com.doodle.task.adapter.out.persistence;

import com.doodle.task.adapter.out.persistence.mapper.SlotMapper;
import com.doodle.task.adapter.out.persistence.repository.SlotJpaRepository;
import com.doodle.task.domain.model.Slot;
import com.doodle.task.domain.model.SlotStatus;
import com.doodle.task.domain.port.out.SlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SlotPersistenceAdapter implements SlotRepository {

    private final SlotJpaRepository jpaRepository;
    private final SlotMapper mapper;

    @Override
    public Slot save(Slot slot) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(slot)));
    }

    @Override
    public Optional<Slot> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public List<Slot> findByUserIdAndStatus(Long userId, SlotStatus status) {
        return jpaRepository.findByUserIdAndStatus(userId, status).stream()
                .map(mapper::toDomain)
                .toList();
    }
}
