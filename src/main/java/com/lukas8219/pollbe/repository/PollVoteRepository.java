package com.lukas8219.pollbe.repository;

import com.lukas8219.pollbe.data.domain.PollVote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollVoteRepository extends CrudRepository<PollVote, Long> {

    @Query("SELECT CASE WHEN COUNT(id) > 0 THEN TRUE ELSE FALSE END FROM PollVote " +
            "WHERE poll.id = :id AND votedBy = :votedBy")
    boolean existsByPollIdAndVotedBy(Long id, Long votedBy);
}
