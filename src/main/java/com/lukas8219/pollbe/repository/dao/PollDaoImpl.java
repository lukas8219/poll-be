package com.lukas8219.pollbe.repository.dao;

import com.lukas8219.pollbe.data.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.JoinType;

@RequiredArgsConstructor
@Repository
public class PollDaoImpl implements PollVoteDao {

    private final EntityManager entityManager;

    @Override
    public boolean existsByPollIdAndVotedBy(Long pollId, Long votedBy) {
        var cb = entityManager.getCriteriaBuilder();
        var query = cb.createQuery(Long.class);
        var from = query.from(PollVote.class);
        var poll = from.<PollVote, Poll>join(PollVote_.POLL, JoinType.INNER);
        var users = from.<PollVote, User>join(PollVote_.VOTED_BY, JoinType.INNER);

        query
                .select(cb.count(from.get(PollVote_.ID)))
                .where(
                        cb.equal(poll.get(Poll_.ID), poll),
                        cb.equal(users.get(User_.ID), votedBy)
                );

        var result = entityManager.createQuery(query).getSingleResult();

        return result != null && result > 0;
    }
}
