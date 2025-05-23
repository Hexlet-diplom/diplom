package com.example.diplom.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

/**
 * Global exception handler for handling application-specific exceptions.
 */
@ControllerAdvice
@NoArgsConstructor
public class GlobalExceptionHandler {

    /**
     * Handles InvalidFilterException by adding an error message to the model.
     * Returns the 400 error view.
     *
     * @param exception the InvalidFilterException thrown
     * @param model                  the model to add attributes to
     * @return the view name for HTTP 400 error page
     */
    @ExceptionHandler(InvalidFilterException.class)
    public String handleInvalidFilter(final InvalidFilterException exception, final Model model) {
        model.addAttribute("errorMessage", exception.getMessage());
        return "error/400";
    }

    /**
     * Handles IllegalArgumentException by adding a generic error message to the model.
     * Returns the 400 error view.
     *
     * @param exception the IllegalArgumentException thrown
     * @param model                    the model to add attributes to
     * @return the view name for HTTP 400 error page
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgument(final IllegalArgumentException exception, final Model model) {
        model.addAttribute("errorMessage", "Invalid request parameters");
        return "error/400";
    }

    /**
     * Handles LessonNotFoundException by returning a ResponseEntity with status 404.
     * The response body contains an error message.
     *
     * @param exception the LessonNotFoundException thrown
     * @return ResponseEntity with error message and HTTP 404 status
     */
    @ExceptionHandler(LessonNotFoundException.class)
    public ResponseEntity<?> handleLessonNotFound(final LessonNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", exception.getMessage()));
    }
}
