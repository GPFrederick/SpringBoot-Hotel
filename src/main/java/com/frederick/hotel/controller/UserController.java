package com.frederick.hotel.controller;

import com.frederick.hotel.dto.RoleDto;
import com.frederick.hotel.dto.UserDto;
import com.frederick.hotel.models.Role;
import com.frederick.hotel.models.User;
import com.frederick.hotel.security.SecurityUtil;
import com.frederick.hotel.service.RoleService;
import com.frederick.hotel.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Controller
public class UserController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<UserDto> users = userService.findAllUsers();
        List<RoleDto> roles = roleService.findAllRoles();
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        return "users-list";
    }

    @GetMapping("/users/{userId}")
    public String userDetail(@PathVariable("userId") long userId, Model model) {
        UserDto userDto = userService.findUserById(userId);
        model.addAttribute("user", userDto);
        return "users-detail";
    }

    @GetMapping("/profile")
    public String userProfile(Model model) {
        String userSession = SecurityUtil.getSessionUser();
        User user = userService.findByEmail(userSession);
        System.out.println("Username: " + user.getUsername());
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/users/new")
    public String createUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "users-create";
    }

    @PostMapping("/users/new")
    public String saveUser(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "users-create";
        }
        userService.saveUser(userDto);
        return "redirect:/users";
    }

    @GetMapping("/users/{userId}/delete")
    public String deleteUser(@PathVariable("userId") long userId) {
        userService.delete(userId);
        return "redirect:/users";
    }

    @GetMapping("/users/{userId}/edit")
    public String editUserForm(@PathVariable("userId") long userId, Model model) {
        UserDto user = userService.findUserById(userId);
        model.addAttribute("user", user);
        return "users-edit";
    }

    @PostMapping("/users/{userId}/edit")
    public String updateUser(@PathVariable("userId") long userId,
                             @Valid @ModelAttribute("user")
                             UserDto userDto,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "users-edit";
        }
        userDto.setId(userId);
        userService.updateUser(userDto);
        return "redirect:/users";
    }

    @GetMapping("/users/search")
    public String searchUser(@RequestParam(value = "query") String query, Model model) {
        List<UserDto> users = userService.searchUsers(query);
        System.out.println("User query: " +  query);
        model.addAttribute("users", users);
        return "users-list";
    }
}
