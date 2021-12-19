package com.lukas8219.pollbe.schedule;

import com.lukas8219.pollbe.service.poll.PollResultReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class PollResultSchedule {

    private final PollResultReportService reportService;

    //@Scheduled(cron = "*/10 * * * * *")
    public void reportPollResult() {
        log.info("Running schedule at [{}]", LocalDateTime.now());
        reportService.reportPollResults();
    }

}
