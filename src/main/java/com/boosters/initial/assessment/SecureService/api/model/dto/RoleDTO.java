package com.boosters.initial.assessment.SecureService.api.model.dto;

import com.boosters.initial.assessment.SecureService.api.enums.RoleEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/** Role DTO */
@Builder
@Getter
@ToString
public class RoleDTO {

    /** Role id */
    private Long idRole;

    /** Role */
    private RoleEnum role;
}
