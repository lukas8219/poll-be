package com.lukas8219.pollbe.repository;

import com.lukas8219.pollbe.data.domain.Poll;
import com.lukas8219.pollbe.data.domain.PollVote;
import com.lukas8219.pollbe.data.dto.PollResultDTO;
import com.lukas8219.pollbe.data.dto.PollResultQueryRow;
import com.lukas8219.pollbe.data.mapper.PollMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.JoinType;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PollResultDaoImp implements PollResultDao {

    private final EntityManager entityManager;
    private final PollMapper mapper;

    @Override
    public List<PollResultDTO> getAllFinishedPolls() {
        //TODO refactor to proper SQL in misc (POLL_REPORT_SQL.sql) AND proper object
        var cb = entityManager.getCriteriaBuilder();
        var query = cb.createQuery(PollResultQueryRow.class);
        var from = query.from(Poll.class);
        var votes = from.<Poll, PollVote>join("votes", JoinType.INNER);

        query.multiselect(
                from.get("id"),
                votes.get("decision"),
                cb.count(votes.get("decision"))
        );

        query.where(
                cb.lessThan(from.get("expiresAt"), LocalDateTime.now()),
                cb.isNull(from.get("reportedAt"))
        );

        query.groupBy(
                from.get("id"),
                votes.get("decision")
        );

        var result = entityManager.createQuery(query)
                .getResultList();
        return mapper.toReportList(result);
    }


    //SELECT COUNT(id) FROM poll.poll_votes WHERE decision = "FAVOR" and poll_id = p.id)
//    private Long getSelectCount(Path<Object> poll, VoteDecisionEnum vote) {
//        var cb = entityManager.getCriteriaBuilder();
//
//        var query = cb.createQuery(Long.class);
//        var from = query.from(PollVote.class);
//
//        query.select(cb.count(from));
//
//        query.where(
//                cb.equal(from.get("decision"), vote),
//                cb.equal(from.get("poll").get("id"), 1L)
//        );
//
//        return entityManager.createQuery(query)
//                .getSingleResult();
//    }
}
