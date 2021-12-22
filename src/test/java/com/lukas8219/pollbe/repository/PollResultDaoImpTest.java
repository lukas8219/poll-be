package com.lukas8219.pollbe.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukas8219.pollbe.data.enumeration.PollResultEnum;
import com.lukas8219.pollbe.data.enumeration.VoteDecisionEnum;
import com.lukas8219.pollbe.data.mapper.PollResultMapperImpl;
import com.lukas8219.pollbe.helper.PollBuilder;
import com.lukas8219.pollbe.helper.PollVoteBuilder;
import com.lukas8219.pollbe.helper.RepositoryWrapper;
import com.lukas8219.pollbe.helper.UserBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import static com.lukas8219.pollbe.data.enumeration.PollResultEnum.APPROVED;
import static com.lukas8219.pollbe.data.enumeration.PollResultEnum.REFUSED;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({
        PollResultDaoImp.class,
        ObjectMapper.class,
        RepositoryWrapper.class,
        DataSourceAutoConfiguration.class,
        PollResultMapperImpl.class
})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PollResultDaoImpTest {

    @Autowired
    private PollResultDao subject;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RepositoryWrapper repository;


    @BeforeAll
    public void setup() {
        assertThat(objectMapper).isNotNull();
        assertThat(subject).isNotNull();
        assertThat(repository).isNotNull();
    }

    @Rollback
    private void createEnvironment() {
        var dev1 = UserBuilder.newBuilder(1L).email("1").build();
        var dev2 = UserBuilder.newBuilder(2L).email("2").build();
        var dev3 = UserBuilder.newBuilder(3L).email("3").build();
        var users = repository.saveAll(dev1, dev2, dev3);

        dev1 = users.get(0);
        dev2 = users.get(1);

        var poll1 = PollBuilder.newBuilder(dev1).buildExpired();
        var poll2 = PollBuilder.newBuilder(dev2).buildExpired();
        var polls = repository.saveAll(poll1, poll2);

        poll1 = polls.get(0);
        poll2 = polls.get(1);

        var vote1 = PollVoteBuilder.of(1L, poll1, VoteDecisionEnum.FAVOR, dev1);
        var vote2 = PollVoteBuilder.of(2L, poll1, VoteDecisionEnum.FAVOR, dev2);
        var vote3 = PollVoteBuilder.of(3L, poll1, VoteDecisionEnum.AGAINST, dev3);

        var vote4 = PollVoteBuilder.of(4L, poll2, VoteDecisionEnum.AGAINST, dev1);
        var vote5 = PollVoteBuilder.of(5L, poll2, VoteDecisionEnum.AGAINST, dev2);
        var vote6 = PollVoteBuilder.of(6L, poll2, VoteDecisionEnum.FAVOR, dev3);
        repository.saveAll(vote1, vote2, vote3, vote4, vote5, vote6);
    }

    @Test
    public void shouldReturnProperAmountOfVotes() {
        createEnvironment();
        var size = subject.getAllFinishedPolls().size();
        assertThat(size).isEqualTo(6);
    }

    @Test
    public void allObjectsShouldHavePollId(){
        createEnvironment();
        var test = subject.getAllFinishedPolls()
                .stream()
                .allMatch(x -> x.getPollId() != null);
        assertThat(test).isTrue();
    }
    @Test
    public void pollWithIdOneShouldBeApproved() {
        pollWithIdShouldBe(1L, APPROVED);
    }

    @Test
    public void pollWithIdTwoShouldBeRefused() {
        pollWithIdShouldBe(2L, REFUSED);
    }

    public void pollWithIdShouldBe(Long id, PollResultEnum resultEnum) {
        createEnvironment();
        var vote = subject.getAllFinishedPolls().stream()
                .filter(result -> result.getPollId() == id)
                .findFirst()
                .orElse(null);

        assertThat(vote).isNotNull();
        assertThat(vote.getResult()).isEqualTo(resultEnum);
    }

}