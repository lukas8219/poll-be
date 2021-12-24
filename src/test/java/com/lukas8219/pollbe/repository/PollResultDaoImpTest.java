package com.lukas8219.pollbe.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukas8219.pollbe.data.enumeration.PollResultEnum;
import com.lukas8219.pollbe.data.mapper.PollResultMapperImpl;
import com.lukas8219.pollbe.helper.*;
import com.lukas8219.pollbe.helper.wrapper.RepositoryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import static com.lukas8219.pollbe.data.enumeration.PollResultEnum.APPROVED;
import static com.lukas8219.pollbe.data.enumeration.PollResultEnum.REFUSED;
import static org.assertj.core.api.Assertions.assertThat;

@Import({
        PollResultDaoImp.class,
        ObjectMapper.class,
        RepositoryWrapper.class,
        DataSourceAutoConfiguration.class,
        PollResultMapperImpl.class
})
@Sql("classpath:PollResultDaoImpTest.sql")
class PollResultDaoImpTest extends DatabaseIT {

    @Autowired
    private PollResultDao subject;

    @Test
    public void shouldReturnProperAmountOfVotes() {
        var size = subject.getAllFinishedPolls().size();
        assertThat(size).isEqualTo(6);
    }

    @Test
    public void allObjectsShouldHavePollId() {
        var test = subject.getAllFinishedPolls()
                .stream()
                .allMatch(x -> x.getPollId() != null);
        assertThat(test).isTrue();
    }

    @Test
    public void allObjectsShouldHaveUser(){
        var test = subject.getAllFinishedPolls()
                .stream()
                .allMatch(x -> x.getUser() != null);
        assertThat(test).isTrue();
    }

    @Test
    public void allObjectShouldHaveResult(){
        var test = subject.getAllFinishedPolls()
                .stream()
                .allMatch(x -> x.getResult() != null);
        assertThat(test).isTrue();
    }

    @Test
    public void allObjectsShouldHaveEmail(){
        var test = subject.getAllFinishedPolls()
                .stream()
                .allMatch(x -> x.getEmail() != null);
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
        var vote = subject.getAllFinishedPolls().stream()
                .filter(result -> result.getPollId() == id)
                .findFirst()
                .orElse(null);

        assertThat(vote).isNotNull();
        assertThat(vote.getResult()).isEqualTo(resultEnum);
    }

}