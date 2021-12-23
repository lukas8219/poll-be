package com.lukas8219.pollbe.repository;

import com.lukas8219.pollbe.data.domain.PollVote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollVoteRepository extends CrudRepository<PollVote, Long> {
}
