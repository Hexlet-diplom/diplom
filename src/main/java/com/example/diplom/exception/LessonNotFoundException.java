package com.example.diplom.exception;

/**
 * Exception thrown when a lesson with the specified ID is not found.
 */
public class LessonNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new LessonNotFoundException with the specified lesson ID.
     *
     * @param lessonId the ID of the lesson that was not found
     */
    public LessonNotFoundException(final Long lessonId) {
        super("Lesson not found with id: " + lessonId);
    }
}

