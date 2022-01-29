package com.lukas8219.pollbe.api;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.dto.*;
import com.lukas8219.pollbe.service.poll.PollFacade;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("creation")
    public PollParticipationDTO getLastCreation(@AuthenticationPrincipal PollUserDetails pollUserDetails){
        return facade.getLastCreation(pollUserDetails);
    }

    @GetMapping("participations")
    public List<PollParticipationDTO> getParticipation(@AuthenticationPrincipal PollUserDetails pollUserDetails){
        return facade.getParticipations(pollUserDetails);
    }

}
