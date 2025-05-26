package com.example.diplom.service;

import com.example.diplom.exception.LessonNotFoundException;
import com.example.diplom.model.Lesson;
import com.example.diplom.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing lessons.
 */
@Service
public class LessonService {

    /**
     * Repository for accessing Lesson entities.
     */
    private final LessonRepository lessonRepository;


    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }
    /**
     * Constructs a LessonService with the given repository.
     *
     * @param lessonRepository the lesson repository
     */
    @Autowired
    public LessonService(final LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    /**
     * Creates a new lesson.
     *
     * @param lesson the lesson to create
     * @return the saved lesson
     */
    public Lesson createLesson(final Lesson lesson) {
        return lessonRepository.save(lesson);
    }


    /**
     * Retrieves a lesson by its ID.
     *
     * @param lessonId the ID of the lesson
     * @return an Optional containing the lesson if found
     */
    public Optional<Lesson> getLessonById(final Long lessonId) {
        return lessonRepository.findById(lessonId);
    }

    /**
     * Retrieves the next lesson in the course sequence.
     *
     * @param currentLessonId the ID of the current lesson
     * @return an Optional containing the next lesson if found
     * @throws LessonNotFoundException if the lesson with the given ID does not exist
     */
    public Optional<Lesson> getNextLesson(final Long currentLessonId) {
        final Lesson currentLesson = lessonRepository.findById(currentLessonId)
                .orElseThrow(() -> new LessonNotFoundException(currentLessonId));

        return lessonRepository.findNextLessons(
                currentLesson.getCourse().getId(),
                currentLesson.getOrderNumber()
        ).stream().findFirst();
    }

    /**
     * Retrieves the previous lesson in the course sequence.
     *
     * @param currentLessonId the ID of the current lesson
     * @return an Optional containing the previous lesson if found
     * @throws LessonNotFoundException if the lesson with the given ID does not exist
     */
    public Optional<Lesson> getPreviousLesson(final Long currentLessonId) {
        final Lesson currentLesson = lessonRepository.findById(currentLessonId)
                .orElseThrow(() -> new LessonNotFoundException(currentLessonId));

        final List<Lesson> previousLessons = lessonRepository.findPreviousLessons(
                currentLesson.getCourse().getId(),
                currentLesson.getOrderNumber()
        );

        return previousLessons.stream().findFirst();
    }

    /**
     * Updates an existing lesson.
     *
     * @param lessonId the ID of the lesson to update
     * @param updatedLesson the lesson data to update
     * @return the updated lesson
     * @throws LessonNotFoundException if lesson not found
     */
    public Lesson updateLesson(final Long lessonId, final Lesson updatedLesson) {
        Lesson existingLesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new LessonNotFoundException(lessonId));
        existingLesson.setName(updatedLesson.getName());
        existingLesson.setContent(updatedLesson.getContent());
        existingLesson.setOrderNumber(updatedLesson.getOrderNumber());
        existingLesson.setCourse(updatedLesson.getCourse());
        existingLesson.setMedia(updatedLesson.getMedia());

        return lessonRepository.save(existingLesson);
    }

    /**
     * Deletes a lesson by ID.
     *
     * @param lessonId the ID of the lesson to delete
     * @throws LessonNotFoundException if lesson not found
     */
    public void deleteLesson(final Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new LessonNotFoundException(lessonId));
        lessonRepository.delete(lesson);
    }

    public long countLessons() {
        return lessonRepository.count();
    }
}
