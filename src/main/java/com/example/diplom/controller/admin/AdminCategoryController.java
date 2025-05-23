package com.example.diplom.controller.admin;

import com.example.diplom.dto.CategoryDto;
import com.example.diplom.mapper.CategoryMapper;
import com.example.diplom.model.Category;
import com.example.diplom.service.CategoryService;
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
@RequestMapping(AdminCategoryController.BASE_PATH)
@RequiredArgsConstructor
public class AdminCategoryController {

    public static final String BASE_PATH = "/admin/dashboard/categories";

    private final CategoryService categoryService;

    @GetMapping
    public String listCategories(Model model) {
        List<CategoryDto> categories = categoryService.getAllCategories().stream()
                .map(CategoryMapper::toDto)
                .collect(Collectors.toList());
        model.addAttribute("categories", categories);
        return "admin/category/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("category", new CategoryDto());
        model.addAttribute("formAction", BASE_PATH);
        return "admin/category/form";
    }

    @PostMapping
    public String createCategory(@Valid @ModelAttribute("category") CategoryDto dto,
                                 BindingResult bindingResult,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("formAction", BASE_PATH);
            return "admin/category/form";
        }

        categoryService.createCategory(dto.getName(), dto.getDescription());
        log.info("Created new category with name '{}'", dto.getName());
        redirectAttributes.addFlashAttribute("successMessage", "Category created successfully");
        return "redirect:" + BASE_PATH;
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Category category = categoryService.getCategoryById(id);

        CategoryDto dto = CategoryMapper.toDto(category);
        model.addAttribute("category", dto);
        model.addAttribute("formAction", BASE_PATH + "/" + id);
        return "admin/category/form";
    }

    @PostMapping("/{id}")
    public String updateCategory(@PathVariable Long id,
                                 @Valid @ModelAttribute("category") CategoryDto dto,
                                 BindingResult bindingResult,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("formAction", BASE_PATH + "/" + id);
            return "admin/category/form";
        }

        categoryService.updateCategory(id, dto.getName(), dto.getDescription());
        log.info("Updated category with id {}", id);
        redirectAttributes.addFlashAttribute("successMessage", "Category updated successfully");
        return "redirect:" + BASE_PATH;
    }

    @PostMapping("/{id}/delete")
    public String deleteCategory(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        categoryService.deleteCategoryById(id);
        log.info("Deleted category with id {}", id);
        redirectAttributes.addFlashAttribute("successMessage", "Category deleted successfully");
        return "redirect:" + BASE_PATH;
    }
}
