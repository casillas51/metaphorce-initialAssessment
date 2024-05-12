package com.boosters.initial.assessment.SecureService.api.model.entity;

import com.boosters.initial.assessment.SecureService.api.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

/**
 * Entity class for role table.
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "roles")
public class RoleEntity {

    /** Role id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Long idRole;

    /** Role name. */
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private RoleEnum role;

    /** Users with this role. */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "role")
    private Set<UserEntity> users;

}
