package com.lukas8219.pollbe.helper.mock;

import com.lukas8219.pollbe.data.dto.PollResultDTO;
import com.lukas8219.pollbe.data.enumeration.PollResultEnum;
import com.lukas8219.pollbe.repository.PollResultDao;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;


public class MockPollResultDao implements PollResultDao {

    private final Supplier<List<PollResultDTO>> supplier;
    private final Consumer<List<Long>> consumer;
    private final PollResult POLL_ONE = new PollResult(1L, PollResultEnum.APPROVED);
    private final PollResult POLL_TWO = new PollResult(2L, PollResultEnum.REFUSED);
    private final MockUser USER_ONE = new MockUser("1", 1L);
    private final MockUser USER_TWO = new MockUser("2", 2L);
    private final MockUser USER_THREE = new MockUser("2", 3L);
    private long updateReportCount = 0;

    public MockPollResultDao(Supplier<List<PollResultDTO>> supplier, Consumer<List<Long>> consumer) {
        this.supplier = supplier;
        this.consumer = consumer;
    }

    public MockPollResultDao() {
        this.supplier = this::getDefaultPolls;
        this.consumer = this::getDefaultConsumer;
    }


    public MockPollResultDao(Supplier<List<PollResultDTO>> supplier) {
        this.supplier = supplier;
        this.consumer = this::getDefaultConsumer;
    }

    public MockPollResultDao(Consumer<List<Long>> consumer) {
        this.supplier = this::getDefaultPolls;
        this.consumer = consumer;
    }

    public long getUpdateReportExecutionCount() {
        return updateReportCount;
    }

    public List<MockUser> getAllUsers() {
        return List.of(USER_ONE, USER_TWO, USER_THREE);
    }

    public <T> List<T> getAllUsersAs(Function<MockUser, T> function) {
        return getAllUsers()
                .stream()
                .map(function)
                .collect(Collectors.toList());
    }

    public void getDefaultConsumer(List<Long> longs) {
    }

    @Override
    public List<PollResultDTO> getAllFinishedPolls() {
        return supplier.get();
    }

    @Override
    public void updateReportedAtForPolls(List<Long> collect) {
        consumer.accept(collect);
        updateReportCount++;
    }

    public List<PollResultDTO> getDefaultPolls() {
        return List.of(
                new PollResultDTO(POLL_ONE.getId(), PollResultEnum.APPROVED, USER_ONE.getId(), USER_ONE.getEmail()),
                new PollResultDTO(POLL_ONE.getId(), PollResultEnum.APPROVED, USER_TWO.getId(), USER_TWO.getEmail()),
                new PollResultDTO(POLL_ONE.getId(), PollResultEnum.REFUSED, USER_THREE.getId(), USER_TWO.getEmail()),
                new PollResultDTO(POLL_TWO.getId(), PollResultEnum.REFUSED, USER_ONE.getId(), USER_ONE.getEmail()),
                new PollResultDTO(POLL_TWO.getId(), PollResultEnum.REFUSED, USER_TWO.getId(), USER_TWO.getEmail()),
                new PollResultDTO(POLL_TWO.getId(), PollResultEnum.APPROVED, USER_THREE.getId(), USER_TWO.getEmail())
        );
    }

    public MockUser getUserOne() {
        return USER_ONE;
    }

    public MockUser getUserTwo() {
        return USER_TWO;
    }

    public MockUser getUserThree() {
        return USER_THREE;
    }

    public List<PollResult> getPolls() {
        return List.of(getPollOne(), getPollTwo());
    }

    public PollResult getPollOne() {
        return POLL_ONE;
    }

    public PollResult getPollTwo() {
        return POLL_TWO;
    }

    public static class MockUser {

        private final String email;
        private final Long id;

        public MockUser(String email, Long id) {
            this.email = email;
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public Long getId() {
            return id;
        }
    }

    public static class PollResult {

        private final Long id;
        private final PollResultEnum result;

        public PollResult(Long id, PollResultEnum result) {
            this.id = id;
            this.result = result;
        }

        public Long getId() {
            return id;
        }

        public PollResultEnum getResult() {
            return result;
        }
    }
}
