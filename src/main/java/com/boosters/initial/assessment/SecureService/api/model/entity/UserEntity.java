package com.boosters.initial.assessment.SecureService.api.model.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * UserEntity
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity {

    /** idUser */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    /** username */
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    /** password */
    @Column(name = "password", nullable = false)
    private String password;

    /** role */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_role", nullable = false)
    private RoleEntity role;
}
