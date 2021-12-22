package com.lukas8219.pollbe.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukas8219.pollbe.data.enumeration.PollResultEnum;
import com.lukas8219.pollbe.data.mapper.PollResultMapperImpl_;
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
import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({
        PollResultDaoImp.class,
        PollResultMapperImpl_.class,
        ObjectMapper.class,
        RepositoryWrapper.class,
        DataSourceAutoConfiguration.class
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
    @Rollback
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
    }

    @Test
    @Rollback
    public void isNotNull() {
        repository.findAllUser();
    }


    @Test
    public void shouldReturnProperAmountOfVotes() {
        var size = subject.getAllFinishedPolls().size();
        assertThat(size).isEqualTo(6);
    }

    @Test
    public void pollWithIdOneShouldBeApproved() {
        pollWithIdShouldBe(1L, APPROVED);
    }

    @Test
    public void pollWithIdTwoShouldBeRefused(){
        pollWithIdShouldBe(2L, REFUSED);
    }

    public void pollWithIdShouldBe(Long id, PollResultEnum resultEnum){
        var vote = subject.getAllFinishedPolls().stream()
                .filter(result -> result.getPollId() == id)
                .findFirst()
                .orElse(null);

        assertThat(vote).isNotNull();
        assertThat(vote.getResult()).isEqualTo(resultEnum);
    }

}