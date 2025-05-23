package com.example.diplom.controller.admin;

import com.example.diplom.dto.RegistrationDto;
import com.example.diplom.dto.UserDto;
import com.example.diplom.mapper.RegistrationMapper;
import com.example.diplom.mapper.RoleMapper;
import com.example.diplom.mapper.UserMapper;
import com.example.diplom.repository.RoleRepository;
import com.example.diplom.service.UserService;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/users")
public final class AdminUserController {

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final RegistrationMapper registrationMapper;

    @SuppressFBWarnings(
            value = "EI_EXPOSE_REP2",
            justification = "Spring-managed service injected via constructor, safe to assign"
    )
    public AdminUserController(UserService userService,
                               RoleRepository roleRepository,
                               UserMapper userMapper,
                               RoleMapper roleMapper, RegistrationMapper registrationMapper) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.registrationMapper = registrationMapper;
    }

    @GetMapping
    public String getUsers(Model model) {
        var userDtos = userService.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
        model.addAttribute("users", userDtos);
        return "admin/users/list";
    }

    @GetMapping("/create")
    public String createUserForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "admin/users/create";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") RegistrationDto userDto) {
        var user = registrationMapper.toEntity(userDto);
        userService.save(user);
        return "redirect:/admin/users";
    }


    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable("id") Long userId, Model model) {
        var user = userService.findById(userId);
        var userDto = userMapper.toDto(user);
        model.addAttribute("user", userDto);
        model.addAttribute("roles", roleMapper.toDtoList(roleRepository.findAll()));
        return "admin/users/edit";
    }


    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long userId,
                           @ModelAttribute("user") UserDto userDto) {
        userDto.setId(userId);
        var existingUser = userService.findById(userId);
        if (existingUser == null) {
            return "redirect:/admin/users?error=notfound";
        }

        // Если пароль не задан — сохраняем старый
        if (userDto.getPassword() == null || userDto.getPassword().isBlank()) {
            userDto.setPassword(existingUser.getPassword());
        }

        userService.save(userMapper.toEntity(userDto));
        return "redirect:/admin/users";
    }


    @GetMapping("/delete/{id}")
    public String confirmDelete(@PathVariable("id") Long userId, Model model) {
        var user = userService.findById(userId);
        model.addAttribute("user", userMapper.toDto(user));
        return "admin/users/delete";
    }


    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long userId) {
        userService.delete(userId);
        return "redirect:/admin/users";
    }
}
