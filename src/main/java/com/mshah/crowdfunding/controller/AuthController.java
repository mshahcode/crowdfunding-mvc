package com.mshah.crowdfunding.controller;

import com.mshah.crowdfunding.model.dto.RegistrationDto;
import com.mshah.crowdfunding.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        model.addAttribute("registration", new RegistrationDto());
        return "register";
    }

    @PostMapping("/register")
    public void register(@Valid @ModelAttribute("registration") RegistrationDto registrationDto) {
        userService.saveUser(registrationDto);
    }
}
