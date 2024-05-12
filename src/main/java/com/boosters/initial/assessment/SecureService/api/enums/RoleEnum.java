package com.boosters.initial.assessment.SecureService.api.enums;

import com.boosters.initial.assessment.SecureService.api.exception.RoleNotExistException;

/**
 * The Enum RoleEnum.
 */
public enum RoleEnum {

    /** Admin role. */
    ADMIN,

    /** User role. */
    USER;

    /** Instantiates a new role enum. */
    RoleEnum() {

    }

    /**
     * Get role enum.
     *
     * @param role the role
     * @return the role enum
     * @throws RoleNotExistException the role not exist exception
     */
    public static RoleEnum getRoleEnum(String role) throws RoleNotExistException {

        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (roleEnum.name().equals(role)) {
                return roleEnum;
            }
        }

        throw new RoleNotExistException(role);
    }
}
