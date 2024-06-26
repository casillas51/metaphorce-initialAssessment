package com.boosters.initial.assessment.SecureService.api.repository;

import com.boosters.initial.assessment.SecureService.api.enums.RoleEnum;
import com.boosters.initial.assessment.SecureService.api.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Role repository */
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    /**
     * Find by role.
     *
     * @param role the role
     * @return Role entity
     */
    RoleEntity findByRole(RoleEnum role);
}
