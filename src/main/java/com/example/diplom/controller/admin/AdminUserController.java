package com.example.diplom.controller.admin;

import com.example.diplom.dto.UserDto;
import com.example.diplom.mapper.UserMapper;
import com.example.diplom.model.Role;
import com.example.diplom.model.User;
import com.example.diplom.repository.RoleRepository;
import com.example.diplom.service.UserService;
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
@RequestMapping(AdminUserController.BASE_PATH)
@RequiredArgsConstructor
public class AdminUserController {

    public static final String BASE_PATH = "/admin/dashboard/users";

    private final UserService userService;
    private final RoleRepository roleRepository;

    @GetMapping
    public String listUsers(Model model) {
        List<UserDto> users = userService.findAll().stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
        model.addAttribute("users", users);
        return "admin/user/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new UserDto());
        model.addAttribute("formAction", BASE_PATH);
        model.addAttribute("allRoles", roleRepository.findAll());
        return "admin/user/form";
    }

    @PostMapping
    public String createUser(@Valid @ModelAttribute("user") UserDto dto,
                             BindingResult bindingResult,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("formAction", BASE_PATH);
            model.addAttribute("allRoles", roleRepository.findAll());
            return "admin/user/form";
        }

        try {
            User user = UserMapper.toEntity(dto, roleRepository); // передаем репозиторий
            userService.save(user);
            redirectAttributes.addFlashAttribute("successMessage", "User created successfully");

        } catch (IllegalArgumentException ex) {
            bindingResult.rejectValue("email", "error.user", ex.getMessage());
            model.addAttribute("formAction", BASE_PATH);
            model.addAttribute("allRoles", roleRepository.findAll());
            return "admin/user/form";
        }

        return "redirect:" + BASE_PATH;
    }



    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        if (user == null) {
            return "redirect:" + BASE_PATH;
        }

        UserDto dto = UserMapper.toDto(user);
        model.addAttribute("user", dto);
        model.addAttribute("formAction", BASE_PATH + "/" + id);
        List<Role> allRoles = roleRepository.findAll();
        model.addAttribute("allRoles", allRoles);
        return "admin/user/form";
    }

    @PostMapping("/{id}")
    public String updateUser(@PathVariable Long id,
                             @Valid @ModelAttribute("user") UserDto dto,
                             BindingResult bindingResult,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("formAction", BASE_PATH + "/" + id);
            model.addAttribute("allRoles", roleRepository.findAll());
            return "admin/user/form";
        }

        try {
            User updatedUser = UserMapper.toEntity(dto, roleRepository);  // передаем репозиторий
            userService.update(id, updatedUser);
            redirectAttributes.addFlashAttribute("successMessage", "User updated successfully");
        } catch (IllegalArgumentException ex) {
            bindingResult.rejectValue("email", "error.user", ex.getMessage());
            model.addAttribute("formAction", BASE_PATH + "/" + id);
            model.addAttribute("allRoles", roleRepository.findAll());
            return "admin/user/form";
        }

        return "redirect:" + BASE_PATH;
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        userService.delete(id);
        log.info("Deleted user with id {}", id);
        redirectAttributes.addFlashAttribute("successMessage", "User deleted successfully");
        return "redirect:" + BASE_PATH;
    }
}
