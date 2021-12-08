package com.lukas8219.pollbe.repository;

import com.lukas8219.pollbe.data.domain.UserPhoto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPhotoRepository extends CrudRepository<UserPhoto, Long> {

    @Query("DELETE FROM UserPhoto WHERE user.id = :id")
    @Modifying
    void deleteByUserId(Long id);
}
