package com.lukas8219.pollbe.helper.builder;

import com.lukas8219.pollbe.data.domain.Poll;
import com.lukas8219.pollbe.data.domain.PollVote;
import com.lukas8219.pollbe.data.domain.User;
import com.lukas8219.pollbe.data.enumeration.VoteDecisionEnum;

import java.time.LocalDateTime;

public class PollVoteBuilder {

    private final PollVote vote = new PollVote();

    private PollVoteBuilder(Long id, Poll poll, VoteDecisionEnum decision, User user) {
        this(poll, decision, user);
        this.vote.setId(id);
    }

    private PollVoteBuilder(Poll poll, VoteDecisionEnum decision, User user) {
        this.vote.setDecision(decision);
        this.vote.setVotedAt(LocalDateTime.now());
        this.vote.setPoll(poll);
        this.vote.setVotedBy(user);
    }

    public static PollVote of(Poll poll, VoteDecisionEnum decision, User user) {
        return of(null, poll, decision, user);
    }

    public static PollVote of(Long id, Poll poll, VoteDecisionEnum decision, User user) {
        if (id == null) {
            return new PollVoteBuilder(poll, decision, user).getVote();
        }
        return new PollVoteBuilder(id, poll, decision, user).getVote();
    }

    public PollVote getVote() {
        return vote;
    }

    public PollVoteBuilder votedAt(LocalDateTime time) {
        this.vote.setVotedAt(time);
        return this;
    }
}
