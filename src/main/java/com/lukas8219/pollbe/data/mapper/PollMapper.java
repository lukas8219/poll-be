package com.lukas8219.pollbe.data.mapper;

import com.lukas8219.pollbe.data.domain.Poll;
import com.lukas8219.pollbe.data.dto.CreatePollDTO;
import com.lukas8219.pollbe.data.dto.PollDTO;
import com.lukas8219.pollbe.data.mapper.decorator.PollDecorator;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@DecoratedWith(PollDecorator.class)
public interface PollMapper {

    Poll toPoll(CreatePollDTO dto);

    PollDTO toDTO(Poll poll);

}
