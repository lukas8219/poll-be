package com.lukas8219.pollbe.api;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.dto.PollResultDTO;
import com.lukas8219.pollbe.data.dto.UserVoteDecisionDTO;
import com.lukas8219.pollbe.data.enumeration.PollResultEnum;
import com.lukas8219.pollbe.data.enumeration.VoteDecisionEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/ws")
public class WebsocketApi {

    private final SimpMessagingTemplate template;

    @GetMapping
    public PollResultDTO chat(@AuthenticationPrincipal PollUserDetails userDetails) {
        var poll = new PollResultDTO();
        var user = new UserVoteDecisionDTO();
        user.setDecision(VoteDecisionEnum.FAVOR);
        user.setId(1L);

        poll.setPollId(1L);
        poll.setResult(PollResultEnum.APPROVED);
        poll.setUsers(Collections.singletonList(user));

        template.convertAndSendToUser(userDetails.getId().toString(), "/queue/", List.of(poll));
        return poll;
    }

}
