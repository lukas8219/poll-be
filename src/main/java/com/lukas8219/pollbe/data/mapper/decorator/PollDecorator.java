package com.lukas8219.pollbe.data.mapper.decorator;


import com.lukas8219.pollbe.data.domain.Poll;
import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.domain.PollVote;
import com.lukas8219.pollbe.data.dto.PollCreatorDetailsDTO;
import com.lukas8219.pollbe.data.dto.PollDTO;
import com.lukas8219.pollbe.data.dto.PollDetailDTO;
import com.lukas8219.pollbe.data.dto.UserVoteDTO;
import com.lukas8219.pollbe.data.enumeration.PollResultEnum;
import com.lukas8219.pollbe.data.enumeration.VoteDecisionEnum;
import com.lukas8219.pollbe.data.mapper.PollMapper;
import com.lukas8219.pollbe.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.lukas8219.pollbe.data.enumeration.VoteDecisionEnum.AGAINST;
import static com.lukas8219.pollbe.data.enumeration.VoteDecisionEnum.FAVOR;

@Component
public abstract class PollDecorator implements PollMapper {

    @Autowired
    private PollMapper delegate;

    @Autowired
    private FileStorageService fileStorageService;

    @Override
    public PollDTO toDTO(Poll poll, PollUserDetails userDetails) {
        var result = delegate.toDTO(poll, userDetails);
        result.setAgainst(countVotes(poll, AGAINST));
        result.setFavor(countVotes(poll, VoteDecisionEnum.FAVOR));
        result.setResult(PollResultEnum.calculate(poll.getExpiresAt(), result.getAgainst(), result.getFavor()));
        result.setVote(getVoteDecision(poll, userDetails));
        return result;
    }

    private VoteDecisionEnum getVoteDecision(Poll poll, PollUserDetails userDetails) {
        if (!CollectionUtils.isEmpty(poll.getVotes())) {
            return poll.getVotes()
                    .stream()
                    .filter(vote -> vote.getVotedBy().equals(userDetails.getId()))
                    .map(PollVote::getDecision)
                    .findFirst()
                    .orElse(null);
        } else {
            return null;
        }
    }

    private Long countVotes(Poll poll, VoteDecisionEnum decision) {
        if (!CollectionUtils.isEmpty(poll.getVotes())) {
            return poll.getVotes().stream()
                    .map(PollVote::getDecision)
                    .filter(vote -> vote == decision)
                    .count();
        } else {
            return 0L;
        }
    }

    @Override
    public PollDetailDTO toPollDetailDTO(Poll poll) {
        var result = delegate.toPollDetailDTO(poll);
        result.setDetail(toDetail(poll));
        result.setAgainst(toUserVote(poll, AGAINST));
        result.setFavor(toUserVote(poll, FAVOR));
        return result;
    }

    @Override
    public PollCreatorDetailsDTO toDetail(Poll source) {
        var result = delegate.toDetail(source);
        result.setUserPhoto(getLink(source));
        return result;
    }

    private String getLink(Poll source) {
        if (source.getCreatedBy() != null) {
            var createdBy = source.getCreatedBy();
            if (createdBy.getPhoto() != null) {
                return fileStorageService.getLink(createdBy.getPhoto());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private List<UserVoteDTO> toUserVote(Poll poll, VoteDecisionEnum vote) {
        return poll.getVotes().stream()
                .filter(x -> x.getDecision() == vote)
                .map(PollVote::getVotedBy)
                .map(x -> {
                    String photo = null;
                    if (x.getPhoto() != null) {
                        photo = fileStorageService.getLink(x.getPhoto());
                    }
                    return new UserVoteDTO(x.getId(), photo, x.getEmail(), x.getName());
                })
                .collect(Collectors.toList());
    }
}
