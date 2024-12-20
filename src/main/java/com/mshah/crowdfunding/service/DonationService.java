package com.mshah.crowdfunding.service;

import com.mshah.crowdfunding.dao.entity.UserEntity;
import com.mshah.crowdfunding.model.dto.DonationFormDto;

public interface DonationService {

    void donateToAnIdea(Long ideaId, UserEntity userEntity, DonationFormDto donationDto);
}
