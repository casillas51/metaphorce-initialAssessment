package com.boosters.initial.assessment.SecureService.api.exception;

import org.springframework.http.HttpStatus;

public class RoleAlreadyExistsException extends SecureServiceException {

    private static final String MESSAGE = "Role %s already exists, can not create a new one";

    private static final String MESSAGE_CODE = "ROLE_ALREADY_EXISTS";

    public RoleAlreadyExistsException(String role) {
        this.setMessage(String.format(MESSAGE, role));
        this.setMessageCode(MESSAGE_CODE);
        this.setHttpStatus(HttpStatus.CONFLICT);
    }
}
