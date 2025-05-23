package com.example.diplom.exception;

/**
 * Exception thrown when a course with the specified ID is not found.
 */
public class CourseNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new CourseNotFoundException
     * with a detailed message including the course ID.
     *
     * @param courseId the ID of the course that was not found
     */
    public CourseNotFoundException(final Long courseId) {
        super("Course not found with id: " + courseId);
    }
}
