package com.boosters.initial.assessment.SecureService.api.repository;

import com.boosters.initial.assessment.SecureService.api.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

}
