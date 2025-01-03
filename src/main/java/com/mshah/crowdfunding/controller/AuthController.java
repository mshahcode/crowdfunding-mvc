package com.mshah.crowdfunding.controller;

import com.mshah.crowdfunding.model.dto.RegistrationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {


    @GetMapping("/login")
    public String getLoginForm() {
        return "login";
    }

    @GetMapping("/signup")
    public String getSignUpForm(Model model) {
        model.addAttribute("registration", new RegistrationDto());
        return "signup";
    }
}
