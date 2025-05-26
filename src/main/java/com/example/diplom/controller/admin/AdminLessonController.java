package com.example.diplom.controller.admin;

import com.example.diplom.dto.LessonDto;
import com.example.diplom.mapper.LessonMapper;
import com.example.diplom.model.Course;
import com.example.diplom.model.Lesson;
import com.example.diplom.model.MediaItem;
import com.example.diplom.repository.CourseRepository;
import com.example.diplom.service.CourseService;
import com.example.diplom.service.LessonService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping(AdminLessonController.BASE_PATH)
@RequiredArgsConstructor
public class AdminLessonController {

    public static final String BASE_PATH = "/admin/dashboard/lessons";

    private final LessonService lessonService;
    private final CourseService courseService;
    private final CourseRepository courseRepository;

    @GetMapping
    public String listLessons(Model model) {
        List<LessonDto> lessons = lessonService.getAllLessons().stream()
                .map(LessonMapper::toDto)
                .collect(Collectors.toList());
        model.addAttribute("lessons", lessons);
        return "admin/lesson/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        LessonDto lessonDto = new LessonDto();
        model.addAttribute("lesson", new LessonDto());
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        model.addAttribute("formAction", BASE_PATH);
        return "admin/lesson/form";
    }

    @PostMapping
    public String createLesson(@Valid @ModelAttribute("lesson") LessonDto dto,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("formAction", BASE_PATH);
            return "admin/lesson/form";
        }

        Course course = courseService.getCourseById(dto.getCourseId());

        List<MediaItem> mediaList = Collections.emptyList();
        if (dto.getMediaJson() != null && !dto.getMediaJson().isEmpty()) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                mediaList = mapper.readValue(dto.getMediaJson(), new TypeReference<List<MediaItem>>() {});
            } catch (Exception e) {
                bindingResult.rejectValue("mediaJson", "error.mediaJson", "Invalid media JSON");
                model.addAttribute("formAction", BASE_PATH);
                model.addAttribute("courses", courseRepository.findAll());
                return "admin/lesson/form";
            }
        }

        Lesson lesson = LessonMapper.toEntity(dto, course);
        lesson.setMedia(mediaList);

        lessonService.createLesson(lesson);

        redirectAttributes.addFlashAttribute("successMessage", "Lesson created successfully");
        return "redirect:" + BASE_PATH;
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Lesson lesson = lessonService.getLessonById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid lesson Id:" + id));
        LessonDto dto = LessonMapper.toDto(lesson);

        ObjectMapper mapper = new ObjectMapper();
        String mediaJson = "";
        try {
            if (lesson.getMedia() != null) {
                mediaJson = mapper.writeValueAsString(lesson.getMedia());
            }
        } catch (Exception e) {
            mediaJson = "[]";
        }
        dto.setMediaJson(mediaJson);

        model.addAttribute("lesson", dto);
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        model.addAttribute("formAction", BASE_PATH + "/" + id);
        return "admin/lesson/form";
    }

    @PostMapping("/{id}")
    public String updateLesson(@PathVariable Long id,
                               @Valid @ModelAttribute("lesson") LessonDto dto,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("formAction", BASE_PATH + "/" + id);
            return "admin/lesson/form";
        }

        Course course = courseService.getCourseById(dto.getCourseId());


        List<MediaItem> mediaList = Collections.emptyList();
        if (dto.getMediaJson() != null && !dto.getMediaJson().isEmpty()) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                mediaList = mapper.readValue(dto.getMediaJson(), new TypeReference<List<MediaItem>>() {});
            } catch (Exception e) {
                bindingResult.rejectValue("mediaJson", "error.mediaJson", "Invalid media JSON");
                model.addAttribute("formAction", BASE_PATH + (id != null ? "/" + id : ""));
                model.addAttribute("courses", courseRepository.findAll());
                return "admin/lesson/form";
            }
        }

        Lesson updatedLesson = LessonMapper.toEntity(dto, course);
        updatedLesson.setMedia(mediaList);

        lessonService.updateLesson(id, updatedLesson);

        log.info("Updated lesson with id {}", id);
        redirectAttributes.addFlashAttribute("successMessage", "Lesson updated successfully");
        return "redirect:" + BASE_PATH;
    }

    @PostMapping("/{id}/delete")
    public String deleteLesson(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        lessonService.deleteLesson(id);

        log.info("Deleted lesson with id {}", id);
        redirectAttributes.addFlashAttribute("successMessage", "Lesson deleted successfully");
        return "redirect:" + BASE_PATH;
    }
}
