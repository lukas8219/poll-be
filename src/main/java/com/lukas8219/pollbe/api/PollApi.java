package com.lukas8219.pollbe.api;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.dto.CreatePollDTO;
import com.lukas8219.pollbe.data.dto.PollDTO;
import com.lukas8219.pollbe.data.dto.PollListDTO;
import com.lukas8219.pollbe.data.dto.PollVoteDTO;
import com.lukas8219.pollbe.data.mapper.PollMapper;
import com.lukas8219.pollbe.exception.PollNotFoundException;
import com.lukas8219.pollbe.repository.PollRepository;
import com.lukas8219.pollbe.service.poll.PollFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/poll")
@RequiredArgsConstructor
public class PollApi {

    private final PollFacade facade;

    @GetMapping
    public List<PollListDTO> getAll(@AuthenticationPrincipal PollUserDetails userDetails) {
        return facade.getAll(userDetails);
    }

    @PostMapping
    public PollDTO create(@AuthenticationPrincipal PollUserDetails userDetails, @Valid @RequestBody CreatePollDTO dto) {
        return facade.create(dto, userDetails);
    }

    @PutMapping("/{id}/approve")
    public PollVoteDTO approve(@AuthenticationPrincipal PollUserDetails userDetails, @PathVariable Long id) {
        return facade.vote(id, true, userDetails);
    }

    @PutMapping("/{id}/refuse")
    public PollVoteDTO refuse(@AuthenticationPrincipal PollUserDetails userDetails, @PathVariable Long id) {
        return facade.vote(id, false, userDetails);
    }

    @GetMapping("{id}")
    public PollDTO getPoll(@AuthenticationPrincipal PollUserDetails userDetails,
                           @PathVariable Long id) {
        return facade.getPoll(id, userDetails);
    }


}
