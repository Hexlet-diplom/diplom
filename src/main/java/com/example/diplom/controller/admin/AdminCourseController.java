package com.example.diplom.controller.admin;

import com.example.diplom.dto.CourseDto;
import com.example.diplom.mapper.CourseMapper;
import com.example.diplom.model.Category;
import com.example.diplom.model.Course;
import com.example.diplom.repository.CategoryRepository;
import com.example.diplom.repository.CourseRepository;
import com.example.diplom.service.CategoryService;
import com.example.diplom.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/courses")
@RequiredArgsConstructor
public class AdminCourseController {

    private final CourseService courseService;
    private final CategoryService categoryService;


}
