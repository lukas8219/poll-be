package com.lukas8219.pollbe.service.poll;

import com.lukas8219.pollbe.data.domain.Poll;
import com.lukas8219.pollbe.data.dto.PollDetailDTO;
import com.lukas8219.pollbe.repository.PollDetailDAO;
import com.lukas8219.pollbe.repository.PollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PollGatewayImpl implements PollGateway {

    private final PollRepository repository;
    private final PollDetailDAO detailDAO;

    @Override
    public Optional<Poll> findByIdAndNotExpired(Long id) {
        return repository.findByIdAndNotExpired(id);
    }

    @Override
    public List<Poll> findAll() {
        return new ArrayList<>(repository.findAll());
    }

    @Override
    public Optional<PollDetailDTO> getDetails(Long id) {
        return detailDAO.getDetails(id);
    }
}
