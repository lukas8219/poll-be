package com.lukas8219.pollbe.data.mapper;

import com.lukas8219.pollbe.data.decorator.PollResultDecorator;
import com.lukas8219.pollbe.data.dto.PollResultDTO;
import com.lukas8219.pollbe.data.dto.PollResultQueryRow;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@DecoratedWith(PollResultDecorator.class)
public interface PollResultMapper {

    List<PollResultDTO> toDTO(List<PollResultQueryRow> result);
}
