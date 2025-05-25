package com.example.diplom.controller.admin;

import com.example.diplom.dto.LessonDto;
import com.example.diplom.mapper.LessonMapper;
import com.example.diplom.model.Course;
import com.example.diplom.model.Lesson;
import com.example.diplom.repository.CourseRepository;
import com.example.diplom.service.CourseService;
import com.example.diplom.service.LessonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        Lesson lesson = LessonMapper.toEntity(dto, course);
        lessonService.createLesson(lesson);


        log.info("Created new lesson with name '{}'", dto.getName());
        redirectAttributes.addFlashAttribute("successMessage", "Lesson created successfully");
        return "redirect:" + BASE_PATH;
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Lesson lesson = lessonService.getLessonById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid lesson Id:" + id));
        LessonDto dto = LessonMapper.toDto(lesson);
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

        Lesson updatedLesson = LessonMapper.toEntity(dto, course);
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
