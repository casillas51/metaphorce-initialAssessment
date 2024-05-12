package com.boosters.initial.assessment.SecureService.api.repository;

import com.boosters.initial.assessment.SecureService.api.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/** User repository */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Find by username ignore case
     *
     * @param username the username
     * @return the user entity
     */
    Optional<UserEntity> findByUsernameIgnoreCase(String username);

}
