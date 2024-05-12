package com.boosters.initial.assessment.SecureService.api.exception;

import org.springframework.http.HttpStatus;

/**
 * The UsernameAlreadyExistsException class.
 */
public class UsernameAlreadyExistsException extends SecureServiceException {

    /** Message to log the failure. */
    private static final String MESSAGE = "Username %s already exists, can not create a new one";

    /** Message code for custom messages. */
    private static final String MESSAGE_CODE = "USERNAME_ALREADY_EXISTS";

    /**
     * Instantiates a new username already exists exception.
     *
     * @param username the username
     */
    public UsernameAlreadyExistsException(String username) {
        this.setMessage(String.format(MESSAGE, username));
        this.setMessageCode(MESSAGE_CODE);
        this.setHttpStatus(HttpStatus.CONFLICT);
    }
}
