package com.example.diplom.controller;

import com.example.diplom.exception.LessonNotFoundException;
import com.example.diplom.helper.MediaHelper;
import com.example.diplom.model.Course;
import com.example.diplom.model.Lesson;
import com.example.diplom.model.MediaItem;
import com.example.diplom.service.CourseService;
import com.example.diplom.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

import java.util.List;
import java.util.Optional;

/**
 * Controller responsible for handling lesson-related requests.
 */
@Controller
@RequestMapping("/lessons")
public class LessonController {

    /**
     * Service for lesson-related business logic.
     */
     private final LessonService lessonService;

    /**
     * Service for course-related business logic.
     */
    private final CourseService courseService;

    /**
     * Helper for processing media items within lessons.
     */
    private final MediaHelper mediaHelper;

    /**
     * Media type constant for images.
     */
    private static final String MEDIA_TYPE_IMAGE = "IMAGE";

    /**
     * Media type constant for code snippets.
     */
    private static final String MEDIA_TYPE_CODE = "CODE";

    /**
     * Constructs a LessonController with the required services and helpers.
     *
     * @param lessonService service for managing lessons
     * @param courseService service for managing courses
     * @param mediaHelper helper for handling media resources
     */
    @Autowired
    public LessonController(final LessonService lessonService,
                            final CourseService courseService,
                            final MediaHelper mediaHelper) {
        this.lessonService = lessonService;
        this.courseService = courseService;
        this.mediaHelper = mediaHelper;
    }

    /**
     * Handles a GET request to display a lesson by ID.
     * Replaces media placeholders with HTML and prepares view model.
     *
     * @param lessonId ID of the lesson
     * @param model    Spring model to render lesson data
     * @return the lesson view template
     * @throws LessonNotFoundException if lesson is not found
     */
    @GetMapping("/{lessonId}")
    public String getLesson(@PathVariable final Long lessonId, final Model model) {
        final Lesson currentLesson = lessonService.getLessonById(lessonId)
                .orElseThrow(() -> new LessonNotFoundException(lessonId));
        final List<MediaItem> mediaItems = currentLesson.getMedia();
        final String originalContent = currentLesson.getContent();

        if (originalContent != null) {
            final String normalizedContent = originalContent
                    .replaceAll("\r\n", "\n")
                    .replaceAll("(\n){3,}", "\n\n");
            currentLesson.setContent(normalizedContent);
        }

        String processedContent = currentLesson.getContent();

        for (int i = 0; i < mediaItems.size(); i++) {
            final MediaItem mediaItem = mediaItems.get(i);
            final String replacement;
            if (MEDIA_TYPE_IMAGE.equals(mediaItem.getType())) {
                replacement = "<div class='image-item'><img src='"
                        + mediaItem.getUrl()
                        + "' alt='Illustration'><div class='image-caption'>"
                        + mediaItem.getCaption()
                        + "</div></div>";
            } else if (MEDIA_TYPE_CODE.equals(mediaItem.getType())) {
                replacement = "<div class='code-example'><pre><code class='language-"
                        + mediaItem.getLanguage()
                        + "'>"
                        + HtmlUtils.htmlEscape(mediaItem.getContent())
                        + "</code></pre></div>";
            } else {
                replacement = "";
            }
            processedContent = processedContent.replace("[[" + mediaItem.getType() + ":" + i + "]]", replacement);
        }

        final Course courseDetails = courseService.getCourseById(currentLesson.getCourse().getId());
        final Optional<Lesson> upcomingLesson = lessonService.getNextLesson(lessonId);

        model.addAttribute("previousLesson", lessonService.getPreviousLesson(lessonId).orElse(null));
        model.addAttribute("lesson", currentLesson);
        model.addAttribute("course", courseDetails);
        model.addAttribute("nextLesson", upcomingLesson.orElse(null));
        model.addAttribute("lessonContentHtml", processedContent);
        model.addAttribute("mediaHelper", mediaHelper);

        return "lessons/lesson";
    }
}
