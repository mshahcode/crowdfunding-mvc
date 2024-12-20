package com.mshah.crowdfunding.mapper.idea;

import com.mshah.crowdfunding.dao.entity.Idea;
import com.mshah.crowdfunding.model.dto.DonationFormDto;
import com.mshah.crowdfunding.model.dto.IdeaCardDto;
import com.mshah.crowdfunding.model.dto.IdeaDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static com.mshah.crowdfunding.model.enums.IdeaStatus.COMPLETED;

@Mapper(componentModel = "spring")
public interface IdeaMapper {

    IdeaCardDto mapToIdeaCardDto(Idea idea);

    @Mapping(target = "owner", source = "user.nickname")
    IdeaDto mapToIdeaDto(Idea idea);

    default void updateIdeaWithDonation(@MappingTarget Idea idea, DonationFormDto donationDto) {
        idea.setDonationsCount(idea.getDonationsCount() + 1);
        idea.setCurrentAmount(idea.getCurrentAmount().add(donationDto.getAmount()));
        if (idea.getCurrentAmount().compareTo(idea.getGoalAmount()) >= 0) {
            idea.setStatus(COMPLETED);
        }
    }
}