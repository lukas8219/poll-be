package com.lukas8219.pollbe.helper.stub;

import com.lukas8219.pollbe.data.dto.PollDetailDTO;
import com.lukas8219.pollbe.data.mapper.PollMapper;
import com.lukas8219.pollbe.repository.PollDetailDAO;
import com.lukas8219.pollbe.repository.PollRepository;

import java.util.Optional;

public class PollDetailDAOStub implements PollDetailDAO {

    private final PollRepository repository;
    private final PollMapper mapper;

    public PollDetailDAOStub(PollRepository repository, PollMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<PollDetailDTO> getDetails(Long id) {
        return repository.findById(id).map(mapper::toPollDetailDTO);
    }
}
