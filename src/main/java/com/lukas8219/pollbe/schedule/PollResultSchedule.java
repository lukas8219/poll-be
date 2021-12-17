package com.lukas8219.pollbe.schedule;

import com.lukas8219.pollbe.repository.PollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PollResultSchedule {

    private final PollRepository repository;
    private final SimpMessagingTemplate messagingTemplate;

    //@Scheduled(cron = "* * * * * *")
    public void reportPollResult() {
        //TODO implement report schedule.
    }

}
