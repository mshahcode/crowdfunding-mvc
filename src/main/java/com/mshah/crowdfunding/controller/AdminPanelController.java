package com.mshah.crowdfunding.controller;

import com.mshah.crowdfunding.model.dto.NewIdeaDto;
import com.mshah.crowdfunding.service.IdeaService;
import com.mshah.crowdfunding.service.ReportService;
import com.mshah.crowdfunding.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("v1/admin/panel")
public class AdminPanelController {

    private final UserService userService;
    private final IdeaService ideaService;
    private final ReportService reportService;

    @GetMapping
    public String adminPanel(Model model) {

        model.addAttribute("reportRequest", new NewIdeaDto());
        model.addAttribute("usersDto", userService.getAllUsers());
        model.addAttribute("totalUsersCount", userService.getTotalUsersCount());
        model.addAttribute("totalActiveUsersCount", userService.getTotalActiveUsersCount());
        model.addAttribute("totalCompletedIdeasCount", ideaService.getTotalCompletedIdeasCount());
        model.addAttribute("reports", reportService.getAllReports());

        return "admin-panel";
    }
}