package com.boosters.initial.assessment.SecureService.api.exception;

import com.boosters.initial.assessment.SecureService.api.response.ServiceErrorResponse;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Handle SecureServiceException.
     *
     * @param secureServiceException the SecureServiceException
     * @return the response entity with error details and appropriate status code
     */
    @ExceptionHandler(SecureServiceException.class)
    public ResponseEntity<String> handleSecureServiceException(SecureServiceException secureServiceException) {
        log.error("Error occurred: {}", secureServiceException.getMessage());
        return new ResponseEntity<>(
                getErrorResponse(secureServiceException),
                secureServiceException.getHttpStatus());
    }

    /**
     * Get error response.
     *
     * @param secureServiceException the SecureServiceException
     * @return the error response
     */
    private String getErrorResponse(SecureServiceException secureServiceException) {
        ServiceErrorResponse serviceErrorResponse = ServiceErrorResponse.builder()
                .errorCode(secureServiceException.getMessageCode())
                .errorMessage(secureServiceException.getMessage())
                .build();

        Gson gson = new Gson();
        return gson.toJson(serviceErrorResponse);
    }

}