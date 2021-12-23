package com.lukas8219.pollbe.repository;

import com.lukas8219.pollbe.data.domain.Poll;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PollRepository extends CrudRepository<Poll, Long> {


    @Query("FROM Poll WHERE id = :id AND expiresAt > NOW()")
    Optional<Poll> findByIdAndNotExpired(Long id);

    List<Poll> findAll();
}
