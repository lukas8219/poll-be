package com.lukas8219.pollbe.data.mapper;

import com.lukas8219.pollbe.data.domain.PollVote;
import com.lukas8219.pollbe.data.dto.PollVoteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PollVoteMapper {

    @Mappings({
            @Mapping(target = "poll", source = "poll.id")
    })
    PollVoteDTO toDTO(PollVote vote);
}
