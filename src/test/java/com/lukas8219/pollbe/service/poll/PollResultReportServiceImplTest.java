package com.lukas8219.pollbe.service.poll;

import com.lukas8219.pollbe.data.dto.PollResultDTO;
import com.lukas8219.pollbe.helper.mock.MockPollResultDao;
import com.lukas8219.pollbe.helper.mock.MockSimpMessageTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class PollResultReportServiceImplTest {

    private MockSimpMessageTemplate<PollResultDTO> messageTemplate;
    private MockPollResultDao mockPollResultDao;
    private PollResultReportService subject;

    @BeforeEach
    public void callMethod() {
        messageTemplate = new MockSimpMessageTemplate<>(PollResultDTO.class, "/queue/");
        mockPollResultDao = new MockPollResultDao();
        subject = new PollResultReportServiceImpl(mockPollResultDao, messageTemplate);
        subject.reportPollResults();
    }

    @Test
    public void sendMessagesToAllUsers() {
        assertThat(messageTemplate.getUsersSent().size()).isEqualTo(mockPollResultDao.getAllFinishedPolls().size());
    }

    @Test
    public void sendMessageToProperUsers() {
        var usersId = mockPollResultDao.getAllUsersAs(user -> user.getId().toString());
        assertThat(messageTemplate.getUsersSent()).containsAll(usersId);
    }

    @Test
    public void updateReportedAtExecutedOneTime() {
        assertThat(mockPollResultDao.getUpdateReportExecutionCount()).isEqualTo(1L);
    }

}