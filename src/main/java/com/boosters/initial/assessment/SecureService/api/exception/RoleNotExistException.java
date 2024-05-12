package com.boosters.initial.assessment.SecureService.api.exception;

import org.springframework.http.HttpStatus;

/**
 * The RoleNotExistException class.
 */
public class RoleNotExistException extends SecureServiceException {

    /** Message for the exception. */
    private static final String MESSAGE = "Role %s does not exist";

    /** Message code for customization. */
    private static final String MESSAGE_CODE = "ROLE_NOT_EXIST";

    /**
     * Instantiates a new role not exist exception.
     *
     * @param role the role
     */
    public RoleNotExistException(String role) {
        this.setMessage(String.format(MESSAGE, role));
        this.setMessageCode(MESSAGE_CODE);
        this.setHttpStatus(HttpStatus.NOT_FOUND);
    }

}
