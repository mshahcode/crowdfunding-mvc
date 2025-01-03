package com.mshah.crowdfunding.util;

import com.mshah.crowdfunding.dao.entity.Donation;
import com.mshah.crowdfunding.dao.entity.Idea;
import com.mshah.crowdfunding.model.dto.DonationFormDto;
import com.mshah.crowdfunding.model.enums.IdeaStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DonationHelper {

    public void validateDonation(Idea idea, DonationFormDto donationDto) {
        if(idea.getStatus() == IdeaStatus.COMPLETED) {
            throw new RuntimeException("Idea with id: " + idea.getId() + " is already completed");
        }

        if(idea.getCurrentAmount().add(donationDto.getAmount()).compareTo(idea.getGoalAmount()) > 0) {
            throw new RuntimeException("Donation amount exceeds the goal amount");
        }
    }

    public void encryptDonation(Donation donation) {
        try {
            donation.setCardNumber(EncryptionUtil.encrypt(donation.getCardNumber()));
            donation.setCardCvv(EncryptionUtil.encrypt(donation.getCardCvv()));
        } catch (Exception e) {
            log.error("DonationHelper.encryptDonation.error: Error while encrypting card number for donation", e);
            throw new RuntimeException("Error while encrypting card number for donation", e);
        }
    }
}
