package com.example.diplom.controller.admin;

import com.example.diplom.dto.CourseDto;
import com.example.diplom.dto.CategoryDto;
import com.example.diplom.mapper.CourseMapper;
import com.example.diplom.model.Category;
import com.example.diplom.model.Course;
import com.example.diplom.service.CategoryService;
import com.example.diplom.service.CourseService;
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

import static com.example.diplom.mapper.CourseMapper.toEntity;

@Slf4j
@Controller
@RequestMapping(AdminCourseController.BASE_PATH)
@RequiredArgsConstructor
public class AdminCourseController {

    public static final String BASE_PATH = "/admin/dashboard/courses";

    private final CourseService courseService;
    private final CategoryService categoryService;

    @GetMapping
    public String listCourses(Model model) {
        List<CourseDto> courses = courseService.getAllCourses().stream()
                .map(CourseMapper::toDto)
                .collect(Collectors.toList());
        model.addAttribute("courses", courses);
        return "admin/course/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("course", new CourseDto());
        model.addAttribute("categories", getCategoryDtos());
        model.addAttribute("formAction", BASE_PATH);
        return "admin/course/form";
    }

    @PostMapping
    public String createCourse(@Valid @ModelAttribute("course") CourseDto dto,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", getCategoryDtos());
            model.addAttribute("formAction", BASE_PATH);
            return "admin/course/form";
        }

        Category category = categoryService.getCategoryById(dto.getCategoryId());

        Course course = toEntity(dto, category);

        courseService.createCourse(course);

        log.info("Created new course with name '{}'", dto.getName());
        redirectAttributes.addFlashAttribute("successMessage", "Course created successfully");
        return "redirect:" + BASE_PATH;
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Course course = courseService.getCourseById(id);
        CourseDto dto = CourseMapper.toDto(course);
        model.addAttribute("course", dto);
        model.addAttribute("categories", getCategoryDtos());
        model.addAttribute("formAction", BASE_PATH + "/" + id);
        return "admin/course/form";
    }

    @PostMapping("/{id}")
    public String updateCourse(@PathVariable Long id,
                               @Valid @ModelAttribute("course") CourseDto dto,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", getCategoryDtos());
            model.addAttribute("formAction", BASE_PATH + "/" + id);
            return "admin/course/form";
        }

        Category category = categoryService.getCategoryById(dto.getCategoryId());
        Course course = toEntity(dto, category); // тот же метод, что в create
        course.setId(id);
        courseService.updateCourse(course);

        log.info("Updated course with id {}", id);
        redirectAttributes.addFlashAttribute("successMessage", "Course updated successfully");
        return "redirect:" + BASE_PATH;
    }

    @PostMapping("/{id}/delete")
    public String deleteCourse(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        courseService.deleteCourseById(id);
        log.info("Deleted course with id {}", id);
        redirectAttributes.addFlashAttribute("successMessage", "Course deleted successfully");
        return "redirect:" + BASE_PATH;
    }

    private List<CategoryDto> getCategoryDtos() {
        return categoryService.getAllCategories().stream()
                .map(c -> {
                    CategoryDto dto = new CategoryDto();
                    dto.setId(c.getId());
                    dto.setName(c.getName());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
