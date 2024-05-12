package com.boosters.initial.assessment.SecureService.api.model.dto;

import com.boosters.initial.assessment.SecureService.api.enums.RoleEnum;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/** Role DTO */
@Builder
@Getter
@ToString
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        creatorVisibility = JsonAutoDetect.Visibility.ANY
)
public class RoleDTO {

    /** Role id */
    private Long idRole;

    /** Role */
    private RoleEnum role;
}
