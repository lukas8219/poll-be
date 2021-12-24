package com.lukas8219.pollbe.service.poll;

import com.lukas8219.pollbe.data.mapper.PollMapperImpl_;
import com.lukas8219.pollbe.helper.PollRepositoryStub;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class PollCreateServiceImplTest {

    private PollCreateServiceImpl subject;

    @BeforeEach
    public void setup() {
        subject = new PollCreateServiceImpl(
                new PollRepositoryStub(),
                new PollMapperImpl_()
        );
    }

    @Test
    public void whenCreate_mustReturn_allFields() {
        //TODO create UserDetailsFactory.
    }

    @Test
    public void whenCreate_mustReturnCorrectly_allFieldValues() {

    }

    @Test
    public void whenCreate_mustPersist() {

    }

    @Test
    public void whenCreated_mustBeCreatedBy_properUser() {

    }
}