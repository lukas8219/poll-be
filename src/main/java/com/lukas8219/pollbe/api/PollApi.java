package com.lukas8219.pollbe.api;

import com.lukas8219.pollbe.data.dto.CreatePollDTO;
import com.lukas8219.pollbe.data.dto.PollDTO;
import com.lukas8219.pollbe.data.dto.PollVoteDTO;
import com.lukas8219.pollbe.service.poll.PollFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/poll")
@RequiredArgsConstructor
public class PollApi {

    private final PollFacade facade;

    @PostMapping
    public PollDTO create(@RequestBody CreatePollDTO dto) {
        return facade.create(dto);
    }

    @PutMapping("/{id}/approve")
    public PollVoteDTO approve(@PathVariable Long id) {
        return facade.vote(id, true);
    }

    @PutMapping("/{id}/refuse")
    public PollVoteDTO refuse(@PathVariable Long id) {
        return facade.vote(id, false);
    }

    @GetMapping("{id}")
    public PollDTO getPoll(@PathVariable Long id) {
        return facade.getPoll(id);
    }

}
