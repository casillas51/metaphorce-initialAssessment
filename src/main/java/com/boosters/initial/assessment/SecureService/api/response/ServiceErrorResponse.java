package com.boosters.initial.assessment.SecureService.api.response;

import lombok.Builder;
import lombok.ToString;

/**
 * Service error response.
 */
@Builder
@ToString
public class ServiceErrorResponse {

    /** The error code. */
    private String errorCode;

    /** The error message. */
    private String errorMessage;
}
