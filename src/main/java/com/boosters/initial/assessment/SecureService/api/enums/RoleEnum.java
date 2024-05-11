package com.boosters.initial.assessment.SecureService.api.enums;

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
     */
    public static RoleEnum getRoleEnum(String role) {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (roleEnum.name().equals(role)) {
                return roleEnum;
            }
        }
        return null;
    }
}
