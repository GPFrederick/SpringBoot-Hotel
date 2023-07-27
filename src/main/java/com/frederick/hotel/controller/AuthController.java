package com.frederick.hotel.controller;

import com.frederick.hotel.dto.RegistrationDto;
import com.frederick.hotel.models.User;
import com.frederick.hotel.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        RegistrationDto registrationDto = new RegistrationDto();
        model.addAttribute("user", registrationDto);
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegistrationDto registrationDto, BindingResult result, Model model) {
        User existingUserEmail = userService.findByEmail(registrationDto.getEmail());
        if (existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()) {
            return "redirect:/register?fail";
        }
        User existingUserUsername = userService.findByUsername(registrationDto.getUsername());
        if (existingUserUsername != null && existingUserUsername.getUsername() != null && !existingUserUsername.getUsername().isEmpty()) {
            return "redirect:/register?fail";
        }
        if (result.hasErrors()) {
            model.addAttribute("user", registrationDto);
            return "redirect:/register";
        }
        userService.createUser(registrationDto);
        return "redirect:/bookings?success";
    }
}
