package com.lukas8219.pollbe.service.poll;

import com.lukas8219.pollbe.data.dto.PollResultDTO;
import com.lukas8219.pollbe.repository.PollResultDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PollResultReportServiceImpl implements PollResultReportService {

    private final PollResultDao resultDao;
    private final SimpMessageSendingOperations messagingTemplate;

    @Override
    public void reportPollResults() {
        var reports = resultDao.getAllFinishedPolls()
                .stream()
                .peek(report -> {
                    messagingTemplate.convertAndSendToUser(report.getUser().toString(), "/queue/", report);
                    log.debug("Sending report to User [{}] for Poll [{}]", report.getUser(), report.getId());
                })
                .collect(Collectors.toList());
        var reportIds = reports.stream().map(PollResultDTO::getId).distinct().collect(Collectors.toList());
        log.debug("Executing update query for all reports with ID [{}]", reportIds);
        resultDao.updateReportedAtForPolls(reportIds);
    }
}
