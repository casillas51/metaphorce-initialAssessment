package com.boosters.initial.assessment.SecureService.api.util;

/**
 * The ValidationUtils class.
 */
public class ValidationUtils {

    /** The instance. */
    private static ValidationUtils instance;

    /**
     * Instantiates a new validation utils.
     */
    private ValidationUtils() {
        // private constructor to prevent instantiation
    }

    /**
     * Gets the single instance of ValidationUtils.
     *
     * @return single instance of ValidationUtils
     */
    public static ValidationUtils validation() {
        if (instance == null) {
            instance = new ValidationUtils();
        }
        return instance;
    }

    /**
     * Validate that array contains null.
     *
     * @param objects the objects to validate
     * @return true, if nulls
     */
    public boolean containsNull(Object... objects) {
        for (Object object : objects) {
            if (null == object) {
                return true;
            }
        }
        return false;
    }
}