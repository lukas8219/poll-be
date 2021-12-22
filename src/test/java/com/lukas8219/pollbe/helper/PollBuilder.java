package com.lukas8219.pollbe.helper;

import com.lukas8219.pollbe.data.domain.Poll;
import com.lukas8219.pollbe.data.domain.PollVote;
import com.lukas8219.pollbe.data.domain.User;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class PollBuilder {

    private final Poll poll = new Poll();

    private PollBuilder(Long id, User user) {
        this(user);
        this.poll.setId(id);
    }

    private PollBuilder(User user) {
        if (user == null || user.getId() == null) {
            throw new RuntimeException("Entidade não persistida. Inválida");
        }
        this.poll.setCreatedBy(user.getId());
        this.poll.setDescription("Descrição");
        this.poll.setExpiresAt(LocalDateTime.now().minusMinutes(5));
        this.poll.setSubject("Assunto");
        this.poll.setVotes(new HashSet<>());
    }

    public static PollBuilder newBuilder(User user) {
        return newBuilder(null, user);
    }

    public static PollBuilder newBuilder(Long id, User user) {
        if (id == null) {
            return new PollBuilder(user);
        } else {
            return new PollBuilder(id, user);
        }
    }

    public Poll buildExpired() {
        return this.poll;
    }

    public Poll build(LocalDateTime date) {
        this.poll.setExpiresAt(date);
        return this.poll;
    }

    public PollBuilder id(Long id) {
        this.poll.setId(id);
        return this;
    }

    public PollBuilder addVote(PollVote vote) {
        this.poll.getVotes().add(vote);
        return this;
    }

    public PollBuilder votes(Set<PollVote> votes) {
        poll.getVotes().clear();
        poll.getVotes().addAll(votes);
        return this;
    }

    public PollBuilder createdBy(User user) {
        this.poll.setCreatedBy(user.getId());
        return this;
    }

    public PollBuilder description(String description) {
        this.poll.setDescription(description);
        return this;
    }

    public PollBuilder expiresAt(LocalDateTime time) {
        this.poll.setExpiresAt(time);
        return this;
    }

    public PollBuilder subject(String subject) {
        this.poll.setSubject(subject);
        return this;
    }
}
