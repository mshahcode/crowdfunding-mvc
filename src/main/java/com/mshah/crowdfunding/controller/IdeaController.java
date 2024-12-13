package com.mshah.crowdfunding.controller;

import com.mshah.crowdfunding.model.dto.IdeaFilterDto;
import com.mshah.crowdfunding.service.IdeaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/ideas")
public class IdeaController {

    private final IdeaService ideaService;

    @GetMapping
    public String getIdeaCards(
            @ModelAttribute("filter") IdeaFilterDto filter,
            Model model
    ) {
        var ideaCards = ideaService.getAllIdeaCards(filter);
        model.addAttribute("ideaCards", ideaCards);

        return "index";
    }
}