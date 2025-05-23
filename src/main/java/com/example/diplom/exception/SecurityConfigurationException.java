package com.example.diplom.exception;

/**
 * Exception thrown when there is a security configuration error in the application.
 */
public class SecurityConfigurationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new SecurityConfigurationException with the specified detail message
     * and cause.
     *
     * @param message the detail message explaining the exception
     * @param cause the cause of the exception (can be retrieved later by {@link Throwable#getCause()})
     */
    public SecurityConfigurationException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
