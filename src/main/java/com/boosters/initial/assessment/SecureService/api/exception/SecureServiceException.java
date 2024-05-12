package com.boosters.initial.assessment.SecureService.api.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * The SecureServiceException class.
 */
@Getter
@Setter
public class SecureServiceException extends Exception {

    /** Message to log the failure. */
    protected String message;

    /** Message code for custom messages. */
    protected String messageCode;

    /** The http status. */
    protected HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    /**
     * Instantiates a new secure service exception.
     */
    public SecureServiceException() {
        super();
    }

}
