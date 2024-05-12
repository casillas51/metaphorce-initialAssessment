package com.boosters.initial.assessment.SecureService.api.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/** User DTO */
@Builder
@Getter
@ToString
public class UserDTO {

    /** User id */
    private Long idUser;

    /** Username */
    private String username;

    /** Password */
    private String password;

    /** Role */
    private String role;

}
