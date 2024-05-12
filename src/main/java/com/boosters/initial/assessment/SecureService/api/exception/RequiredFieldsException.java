package com.boosters.initial.assessment.SecureService.api.exception;

import org.springframework.http.HttpStatus;

/**
 * The RequiredFieldsException class.
 */
public class RequiredFieldsException extends  SecureServiceException {

    /** Message for the exception. */
    private static final String MESSAGE = "Required fields are missing";

    /** Message code for customization. */
    private static final String MESSAGE_CODE = "REQUIRED_FIELDS";

    /**
     * Instantiates a new required fields exception.
     */
    public RequiredFieldsException() {
        this.setMessage(MESSAGE);
        this.setMessageCode(MESSAGE_CODE);
        this.setHttpStatus(HttpStatus.FAILED_DEPENDENCY);
    }
}
