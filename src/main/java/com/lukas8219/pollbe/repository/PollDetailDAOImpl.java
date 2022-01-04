package com.lukas8219.pollbe.repository;

import com.lukas8219.pollbe.data.dto.PollDetailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PollDetailDAOImpl implements PollDetailDAO {

    private final EntityManager entityManager;

    @Override
    public Optional<PollDetailDTO> getDetails(Long id) {
        var cb = entityManager.getCriteriaBuilder();
        var query = cb.createQuery();
        return Optional.empty();
    }

}
