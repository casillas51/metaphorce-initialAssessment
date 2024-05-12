package com.boosters.initial.assessment.SecureService.api.exception;

public class UserNotExistException extends SecureServiceException {

    /** Message for the exception. */
    private static final String MESSAGE = "User %d does not exist";

    /** Message code for customization. */
    private static final String MESSAGE_CODE = "USER_NOT_EXIST";

    /**
     * Instantiates a new role not exist exception.
     *
     * @param user the user
     */
    public UserNotExistException(Long user) {
        this.setMessage(String.format(MESSAGE, user));
        this.setMessageCode(MESSAGE_CODE);
    }
}

