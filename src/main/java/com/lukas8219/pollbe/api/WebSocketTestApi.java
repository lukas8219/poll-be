package com.lukas8219.pollbe.api;

import com.lukas8219.pollbe.config.ApplicationConfiguration;
import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.enumeration.EnvironmentEnum;
import com.lukas8219.pollbe.data.mapper.PollMapper;
import com.lukas8219.pollbe.exception.NotFoundException;
import com.lukas8219.pollbe.exception.PollNotFoundException;
import com.lukas8219.pollbe.repository.PollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ws/{pollId}")
@RequiredArgsConstructor
public class WebSocketTestApi {

    private final PollRepository repository;
    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final PollMapper pollMapper;

    private final ApplicationConfiguration configuration;

    @GetMapping
    public void notify(@PathVariable Long pollId,
                       @AuthenticationPrincipal PollUserDetails pollUserDetails) {
        if (configuration.getEnvironment() == EnvironmentEnum.DEVELOPMENT) {
            var poll = repository.findById(pollId).orElseThrow(PollNotFoundException::new);
            simpMessageSendingOperations.convertAndSendToUser(pollUserDetails.getId().toString(), "/queue/", pollMapper.toDTO(poll, pollUserDetails));
        } else {
            throw new NotFoundException();
        }

    }

}
