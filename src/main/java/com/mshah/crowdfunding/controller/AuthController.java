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

    @GetMapping("/login")
    public String getLoginForm() {
        return "login";
    }

    @GetMapping("/signup")
    public String getSignUpForm(Model model) {
        model.addAttribute("registration", new RegistrationDto());
        return "signup";
    }

    @PostMapping("/signup")
    public String signUpUser(@Valid @ModelAttribute("registration") RegistrationDto registrationDto) {
        userService.signUpUser(registrationDto);
        return "redirect:/login";
    }
}
