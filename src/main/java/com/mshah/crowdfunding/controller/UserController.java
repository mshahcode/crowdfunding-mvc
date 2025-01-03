package com.mshah.crowdfunding.controller;

import com.mshah.crowdfunding.model.dto.RegistrationDto;
import com.mshah.crowdfunding.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/activate/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String activateUser(@PathVariable Long id) {
        userService.activateUser(id);
        return "redirect:/v1/admin/panel";
    }

    @PostMapping("/deactivate/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deActivateUser(@PathVariable Long id) {
        userService.deActivateUser(id);
        return "redirect:/v1/admin/panel";
    }

    @PostMapping("/signup")
    public String signUpUser(@Valid @ModelAttribute("registration") RegistrationDto registrationDto) {
        userService.signUpUser(registrationDto);
        return "redirect:/v1/ideas";
    }
}
