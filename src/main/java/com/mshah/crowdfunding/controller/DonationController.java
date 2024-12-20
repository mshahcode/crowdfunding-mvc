package com.mshah.crowdfunding.controller;

import com.mshah.crowdfunding.model.dto.DonationFormDto;
import com.mshah.crowdfunding.security.CustomUserDetails;
import com.mshah.crowdfunding.service.DonationService;
import com.mshah.crowdfunding.service.IdeaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/v1/donations")
@RequiredArgsConstructor
public class DonationController {

    private final IdeaService ideaService;
    private final DonationService donationService;


    @GetMapping("/ideas/{id}")
    public String getDonationForm(
            @PathVariable @Min(0) Long id,
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            Model model
    ) {
        var ideaDto = ideaService.getIdeaById(id);

        model.addAttribute("donationForm", new DonationFormDto());
        model.addAttribute("activeUser", customUserDetails.getUserEntity());
        model.addAttribute("ideaDto", ideaDto);

        return "donation-form";
    }

    @PostMapping("/ideas/{id}")
    public String donateToAnIdea(
            @PathVariable @Min(0) Long id,
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @Valid @ModelAttribute("donationForm") DonationFormDto donationFormDto,
            BindingResult bindingResult,
            Model model
    ) {

        System.out.println(donationFormDto);

        if (bindingResult.hasErrors()) {
            var ideaDto = ideaService.getIdeaById(id);
            model.addAttribute("ideaDto", ideaDto);
            model.addAttribute("activeUser", customUserDetails.getUserEntity());
            return "donation-form";
        }

        donationService.donateToAnIdea(id, customUserDetails.getUserEntity(), donationFormDto);

        return "redirect:/v1/ideas/" + id;
    }
}
