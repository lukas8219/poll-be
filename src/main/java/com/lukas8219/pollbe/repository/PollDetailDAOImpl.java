package com.lukas8219.pollbe.repository;

import com.lukas8219.pollbe.data.domain.Poll;
import com.lukas8219.pollbe.data.domain.Poll_;
import com.lukas8219.pollbe.data.dto.PollDetailDTO;
import com.lukas8219.pollbe.data.mapper.PollMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PollDetailDAOImpl implements PollDetailDAO {

    private final EntityManager entityManager;
    private final PollMapper mapper;

    @Override
    public Optional<PollDetailDTO> getDetails(Long id) {
        var poll = fetchPoll(id);
        var detail = mapper.toPollDetailDTO(poll);
        return Optional.ofNullable(detail);
    }

    private Poll fetchPoll(Long id) {
        var cb = entityManager.getCriteriaBuilder();
        var query = cb.createQuery(Poll.class);
        var from = query.from(Poll.class);

        from.fetch(Poll_.CREATED_BY);
        from.fetch(Poll_.VOTES);

        query.where(cb.equal(from.get(Poll_.ID), id));

        return entityManager.createQuery(query).getSingleResult();
    }

}
