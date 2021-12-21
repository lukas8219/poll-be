package com.lukas8219.pollbe.repository;

import com.lukas8219.pollbe.data.domain.Poll;
import com.lukas8219.pollbe.data.domain.PollVote;
import com.lukas8219.pollbe.data.domain.User;
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
        //TODO refactor to proper SQL in misc (POLL_REPORT_SQL.sql) AND proper object
        var cb = entityManager.getCriteriaBuilder();
        var query = cb.createQuery(PollResultQueryRow.class);
        var from = query.from(Poll.class);
        var vote = from.<Poll, PollVote>join("votes", JoinType.INNER);
        var user = from.<PollVote, User>join("user", JoinType.INNER);

        query.multiselect(
                from.get("id"),
                vote.get("decision"),
                user.get("id"),
                user.get("email")
        );

        query.where(
                cb.lessThan(from.get("expiresAt"), LocalDateTime.now()),
                cb.isNull(from.get("reportedAt"))
        );

        query.groupBy(
                from.get("id"),
                vote.get("decision"),
                user.get("id"),
                user.get("email")
        );

        //TODO Never do this Horror
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
        update.set("reportedAt", LocalDateTime.now());
        update.where(
                cb.lessThan(from.get("expiresAt"), LocalDateTime.now()),
                cb.isNull(from.get("reportedAt"))
        );
        entityManager.createQuery(update).executeUpdate();
    }


    private List<Long> getUsersThatVoted(Long pollId) {
        var cb = entityManager.getCriteriaBuilder();
        var query = cb.createQuery(Long.class);
        var from = query.from(PollVote.class);
        var poll = from.join("poll");
        query.select(from.get("votedBy"));
        query.where(cb.equal(poll.get("id"), pollId));
        return entityManager.createQuery(query)
                .getResultList();

    }

}
