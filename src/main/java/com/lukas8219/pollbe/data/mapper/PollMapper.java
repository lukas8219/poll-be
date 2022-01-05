package com.lukas8219.pollbe.data.mapper;

import com.lukas8219.pollbe.data.domain.Poll;
import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.dto.CreatePollDTO;
import com.lukas8219.pollbe.data.dto.PollCreatorDetailsDTO;
import com.lukas8219.pollbe.data.dto.PollDTO;
import com.lukas8219.pollbe.data.dto.PollDetailDTO;
import com.lukas8219.pollbe.data.mapper.decorator.PollDecorator;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@DecoratedWith(PollDecorator.class)
public interface PollMapper {

    Poll toPoll(CreatePollDTO dto);

    @Mappings({
            @Mapping(target = "id", source = "poll.id"),
            @Mapping(target = "subject", source = "poll.subject"),
            @Mapping(target = "description", source = "poll.description"),
            @Mapping(target = "expiresAt", source = "poll.expiresAt"),
            @Mapping(target = "createdBy", source = "poll.createdBy.id")
    })
    PollDTO toDTO(Poll poll, PollUserDetails userDetails);

    PollDetailDTO toPollDetailDTO(Poll poll);


    @Mappings({
            @Mapping(target = "userPhoto", ignore = true),
            @Mapping(target = "userEmail", source = "createdBy.email"),
            @Mapping(target = "userName", source = "createdBy.name"),
            @Mapping(target = "userId", source = "createdBy.id"),
            @Mapping(target = "pollDescription", source = "description"),
            @Mapping(target = "pollSubject", source = "subject"),
            @Mapping(target = "pollExpiresAt", source = "expiresAt"),
            @Mapping(target = "pollReportedAt", source = "reportedAt"),
    })
    PollCreatorDetailsDTO toDetail(Poll source);
}
