package com.mshah.crowdfunding.controller;

import com.mshah.crowdfunding.model.dto.IdeaFilterDto;
import com.mshah.crowdfunding.model.dto.NewIdeaDto;
import com.mshah.crowdfunding.security.AuthenticatedUserService;
import com.mshah.crowdfunding.security.CustomUserDetails;
import com.mshah.crowdfunding.service.IdeaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/ideas")
public class IdeaController {

    private final IdeaService ideaService;
    private final AuthenticatedUserService authenticatedUserService;

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
            @Validated @PathVariable @Min(0) Long id,
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            Model model
    ) {
        var ideaDto = ideaService.getIdeaById(id);

        model.addAttribute("activeUser", customUserDetails.getUserEntity());
        model.addAttribute("ideaDto", ideaDto);

        return "idea-details";
    }

    @GetMapping("/manage")
    public String manageIdeaForm(
            Model model
    ) {
        var currentUser = authenticatedUserService.loadCurrentUser();
        var currentUserIdeas = ideaService.getIdeasByUser(currentUser.getId());

        model.addAttribute("activeUser", currentUser);
        model.addAttribute("currentUserIdeas", currentUserIdeas);
        model.addAttribute("newIdea", new NewIdeaDto());
        return "manage-ideas";
    }

    @PostMapping("/new")
    public String createNewIdea(
            @Valid @ModelAttribute("newIdea") NewIdeaDto newIdeaDto,
            BindingResult bindingResult,
            ModelMap model
    ) {
        var currentUser = authenticatedUserService.loadCurrentUser();

        if (bindingResult.hasErrors()) {
            model.addAttribute("activeUser", currentUser);
            model.addAttribute("currentUserIdeas", ideaService.getIdeasByUser(currentUser.getId()));
            return "manage-ideas";
        }

        ideaService.createNewIdea(newIdeaDto, currentUser);

        return "redirect:/v1/ideas/manage";
    }
}