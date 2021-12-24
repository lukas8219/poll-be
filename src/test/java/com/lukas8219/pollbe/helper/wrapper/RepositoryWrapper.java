package com.lukas8219.pollbe.helper.wrapper;


import com.lukas8219.pollbe.data.domain.Poll;
import com.lukas8219.pollbe.data.domain.PollVote;
import com.lukas8219.pollbe.data.domain.User;
import com.lukas8219.pollbe.repository.PollRepository;
import com.lukas8219.pollbe.repository.PollVoteRepository;
import com.lukas8219.pollbe.repository.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@TestComponent
public class RepositoryWrapper implements InitializingBean {

    private final UserRepository userRepository;
    private final PollRepository pollRepository;
    private final PollVoteRepository voteRepository;

    @Autowired
    public RepositoryWrapper(UserRepository userRepository, PollRepository pollRepository, PollVoteRepository voteRepository) {
        this.userRepository = userRepository;
        this.pollRepository = pollRepository;
        this.voteRepository = voteRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Poll savePoll(Poll poll) {
        return pollRepository.save(poll);
    }

    public PollVote savePollVote(PollVote vote) {
        return voteRepository.save(vote);
    }

    public List<User> saveAll(User... users) {
        return saveAll(Arrays.asList(users), userRepository);
    }

    public List<Poll> saveAll(Poll... polls) {
        return saveAll(Arrays.asList(polls), pollRepository);
    }

    public List<PollVote> saveAll(PollVote... votes) {
        return saveAll(Arrays.asList(votes), voteRepository);
    }

    public Collection<User> findAllUser() {
        var result = new ArrayList<User>();
        userRepository.findAll().forEach(result::add);
        return result;
    }

    public Collection<Poll> findAllPoll() {
        var result = new ArrayList<Poll>();
        pollRepository.findAll().forEach(result::add);
        return result;
    }

    public Collection<PollVote> findAllVotes() {
        var result = new ArrayList<PollVote>();
        voteRepository.findAll().forEach(result::add);
        return result;
    }

    private <T> List<T> saveAll(List<T> toSave, CrudRepository<T, Long> repository) {
        var result = new ArrayList<T>();
        repository.saveAll(toSave)
                .forEach(result::add);
        return result;
    }

    @Override

    public void afterPropertiesSet() throws Exception {
        if (userRepository == null) {
            throw new RuntimeException("UserRepository is null. Please check the Bean setup.");
        }

        if (pollRepository == null) {
            throw new RuntimeException("UserRepository is null. Please check the Bean setup.");
        }

        if (voteRepository == null) {
            throw new RuntimeException("UserRepository is null. Please check the Bean setup.");
        }
    }

    public void clean() {
        voteRepository.deleteAll();
        pollRepository.deleteAll();
        userRepository.deleteAll();
    }
}
