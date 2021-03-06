package com.lukas8219.pollbe.data.mapper;

import com.lukas8219.pollbe.data.domain.Poll;
import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.dto.*;
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
            @Mapping(target = "expiresAt", source = "poll.expiresAt")
    })
    PollDTO toDTO(Poll poll, PollUserDetails userDetails);

    @Mappings({
            @Mapping(target = "id", source = "poll.id"),
            @Mapping(target = "subject", source = "poll.subject"),
    })
    PollListDTO toListDTO(Poll poll, PollUserDetails userDetails);
}
