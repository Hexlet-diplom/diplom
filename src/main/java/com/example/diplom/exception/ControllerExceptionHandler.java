package com.example.diplom.exception;

import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for controllers.
 * Handles specific exceptions and maps them to error views.
 */
@ControllerAdvice
@NoArgsConstructor
public class ControllerExceptionHandler {

    /**
     * Handles CourseNotFoundException by returning the 404 error page.
     *
     * @return the name of the 404 error view
     */
    @ExceptionHandler(CourseNotFoundException.class)
    public String handleCourseNotFound() {
        return "error/404";
    }
}
