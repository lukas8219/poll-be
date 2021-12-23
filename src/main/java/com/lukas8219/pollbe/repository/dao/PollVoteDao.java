package com.lukas8219.pollbe.repository.dao;

public interface PollVoteDao {

    boolean existsByPollIdAndVotedBy(Long pollId, Long votedBy);

}
