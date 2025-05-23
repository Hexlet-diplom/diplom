package com.example.diplom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when an invalid filter is applied.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidFilterException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new InvalidFilterException with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidFilterException(final String message) {
        super(message);
    }
}
