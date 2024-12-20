package com.mshah.crowdfunding.service.impl;

import com.mshah.crowdfunding.dao.entity.UserEntity;
import com.mshah.crowdfunding.dao.repository.DonationRepository;
import com.mshah.crowdfunding.dao.repository.IdeaRepository;
import com.mshah.crowdfunding.mapper.donation.DonationMapper;
import com.mshah.crowdfunding.mapper.idea.IdeaMapper;
import com.mshah.crowdfunding.model.dto.DonationFormDto;
import com.mshah.crowdfunding.service.DonationService;
import com.mshah.crowdfunding.util.DonationHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DonationServiceImpl implements DonationService {

    private final DonationRepository donationRepository;
    private final IdeaRepository ideaRepository;
    private final DonationMapper donationMapper;
    private final IdeaMapper ideaMapper;
    private final DonationHelper donationHelper;

    @Transactional
    public void donateToAnIdea(Long ideaId, UserEntity user, DonationFormDto donationDto) {
        log.info("DonationServiceImpl.donateToAnIdea.start: donating to idea with id: {} by user with id: {}", ideaId, user.getId());

        var idea = ideaRepository.findById(ideaId).orElseThrow(() -> {
            log.error("DonationServiceImpl.donateToAnIdea.error: Idea not found with id: {}", ideaId);
            return new RuntimeException("Idea not found with id: " + ideaId);
        });

        donationHelper.validateDonation(idea, donationDto);

        var donation = donationMapper.mapToDonationEntity(idea, user, donationDto);

        donationHelper.encryptDonation(donation);

        ideaMapper.updateIdeaWithDonation(idea, donationDto);

        donationRepository.save(donation);

        log.info("DonationServiceImpl.donateToAnIdea.end: donated to idea with id: {} by user with id: {}", ideaId, user.getId());
    }
}