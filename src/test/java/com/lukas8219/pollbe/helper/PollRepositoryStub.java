package com.lukas8219.pollbe.helper;

import com.lukas8219.pollbe.data.domain.Poll;
import com.lukas8219.pollbe.repository.PollRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class PollRepositoryStub extends GenericIncrementalRepositoryStub<Poll, Long> implements PollRepository {

    @Override
    public Optional<Poll> findByIdAndNotExpired(Long id) {
        Poll e;
        if (id > database.size()) {
            e = null;
        } else {
            e = database.get(Math.toIntExact(id));
            if (e.getExpiresAt().isBefore(LocalDateTime.now())) {
                e = null;
            }
        }
        return Optional.ofNullable(e);
    }

    @Override
    public List<Poll> findAll() {
        return database;
    }


    @Override
    public <S extends Poll> S save(S entity) {
        database.add(entity);
        if (entity.getId() == null) {
            entity.setId((long) database.size());
        }
        return entity;
    }
}
