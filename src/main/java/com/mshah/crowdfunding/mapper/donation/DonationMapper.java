package com.mshah.crowdfunding.mapper.donation;

import com.mshah.crowdfunding.dao.entity.Donation;
import com.mshah.crowdfunding.dao.entity.Idea;
import com.mshah.crowdfunding.dao.entity.UserEntity;
import com.mshah.crowdfunding.model.dto.DonationFormDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DonationMapper {

    @Mapping(ignore = true, target = "id")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "idea", source = "idea")
    @Mapping(target = "amount", source = "donationDto.amount")
    @Mapping(target = "cardType", source = "donationDto.cardType")
    @Mapping(target = "cardNumber", source = "donationDto.cardNumber")
    @Mapping(target = "cardCvv", source = "donationDto.cardCvv")
    @Mapping(target = "cardExpDate", source = "donationDto.cardExpDate")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    Donation mapToDonationEntity(Idea idea, UserEntity user, DonationFormDto donationDto);
}
