package com.mshah.crowdfunding.controller;

import com.mshah.crowdfunding.model.dto.IdeaFilterDto;
import com.mshah.crowdfunding.security.CustomUserDetails;
import com.mshah.crowdfunding.service.IdeaService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/ideas")
public class IdeaController {

    private final IdeaService ideaService;

    @GetMapping
    public String getIdeaCards(
            @ModelAttribute("filter") IdeaFilterDto filter,
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            Model model
    ) {
        var ideaCards = ideaService.getAllIdeaCards(filter);

        model.addAttribute("ideaCards", ideaCards);
        model.addAttribute("activeUser", customUserDetails.getUserEntity());

        return "index";
    }

    @GetMapping("/{id}")
    public String getIdeaDetails(
            @PathVariable @Min(0) Long id,
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            Model model
    ) {
        var ideaDto = ideaService.getIdeaById(id);

        model.addAttribute("activeUser", customUserDetails.getUserEntity());
        model.addAttribute("ideaDto", ideaDto);

        return "idea-details";
    }
}