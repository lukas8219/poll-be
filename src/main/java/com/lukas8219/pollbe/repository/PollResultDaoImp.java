package com.lukas8219.pollbe.repository;

import com.lukas8219.pollbe.data.domain.*;
import com.lukas8219.pollbe.data.dto.PollResultDTO;
import com.lukas8219.pollbe.data.dto.PollResultQueryRow;
import com.lukas8219.pollbe.data.mapper.PollResultMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.JoinType;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PollResultDaoImp implements PollResultDao {

    private final EntityManager entityManager;
    private final PollResultMapper mapper;

    @Override
    public List<PollResultDTO> getAllFinishedPolls() {
        var cb = entityManager.getCriteriaBuilder();
        var query = cb.createQuery(PollResultQueryRow.class);
        var from = query.from(Poll.class);
        var vote = from.<Poll, PollVote>join(Poll_.VOTES, JoinType.INNER);
        var user = vote.<PollVote, User>join(PollVote_.VOTED_BY, JoinType.INNER);


        query.multiselect(
                from.get(Poll_.ID),
                vote.get(PollVote_.DECISION),
                user.get(User_.ID),
                user.get(User_.EMAIL)
        );

        query.where(
                cb.lessThan(from.get(Poll_.EXPIRES_AT), LocalDateTime.now()),
                cb.isNull(from.get(Poll_.REPORTED_AT))
        );

        query.groupBy(
                from.get(Poll_.ID),
                vote.get(PollVote_.DECISION),
                user.get(User_.ID),
                user.get(User_.EMAIL)
        );

        var result = entityManager.createQuery(query)
                .getResultList();
        return mapper.toDTO(result);
    }

    @Override
    @Transactional
    public void updateReportedAtForPolls(List<Long> collect) {
        var cb = entityManager.getCriteriaBuilder();
        var update = cb.createCriteriaUpdate(Poll.class);
        var from = update.from(Poll.class);
        update.set(Poll_.REPORTED_AT, LocalDateTime.now());
        update.where(
                cb.lessThan(from.get(Poll_.EXPIRES_AT), LocalDateTime.now()),
                cb.isNull(from.get(Poll_.REPORTED_AT))
        );
        entityManager.createQuery(update).executeUpdate();
    }

}
