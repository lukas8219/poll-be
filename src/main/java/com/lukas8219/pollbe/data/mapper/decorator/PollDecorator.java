package com.lukas8219.pollbe.data.mapper.decorator;


import com.lukas8219.pollbe.data.domain.Poll;
import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.domain.PollVote;
import com.lukas8219.pollbe.data.domain.User;
import com.lukas8219.pollbe.data.dto.PollCreatorDetailsDTO;
import com.lukas8219.pollbe.data.dto.PollDTO;
import com.lukas8219.pollbe.data.dto.PollListDTO;
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
        result.setUsersVotes(toUserVotes(poll));
        result.setCreator(toCreator(poll));
        result.setCreatedAt(poll.getCreatedAt());
        return result;
    }

    private PollCreatorDetailsDTO toCreator(Poll poll) {
        var user = poll.getCreatedBy();
        return new PollCreatorDetailsDTO(
                getPhotoUrl(user),
                user.getEmail(),
                user.getName(),
                user.getId()
        );
    }

    private List<UserVoteDTO> toUserVotes(Poll poll) {
        if (!CollectionUtils.isEmpty(poll.getVotes())) {
            return poll.getVotes().stream()
                    .map(this::toUserVoteDTO)
                    .collect(Collectors.toList());
        } else {
            return null;
        }
    }

    private VoteDecisionEnum getVoteDecision(Poll poll, PollUserDetails userDetails) {
        if (!CollectionUtils.isEmpty(poll.getVotes())) {
            return poll.getVotes()
                    .stream()
                    .filter(vote -> vote.getVotedBy().getId().equals(userDetails.getId()))
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

    private UserVoteDTO toUserVoteDTO(PollVote pollVote) {

        var user = pollVote.getVotedBy();
        String photo = getPhotoUrl(user);

        return new UserVoteDTO(
                user.getId(),
                user.getEmail(),
                user.getName(),
                pollVote.getDecision(),
                photo
        );
    }

    private String getPhotoUrl(User user) {
        String photo = null;
        if (user.getPhoto() != null) {
            photo = fileStorageService.getLink(user.getPhoto());
        }
        return photo;
    }


    @Override
    public PollListDTO toListDTO(Poll poll, PollUserDetails userDetails) {
        var result = delegate.toListDTO(poll, userDetails);
        var favor = countVotes(poll, FAVOR);
        var against = countVotes(poll, AGAINST);
        result.setVote(getVoteDecision(poll, userDetails));
        result.setResult(PollResultEnum.calculate(poll.getExpiresAt(), against, favor));
        return result;
    }
}
