package com.mshah.crowdfunding.mapper.user;

import com.mshah.crowdfunding.dao.entity.UserEntity;
import com.mshah.crowdfunding.model.dto.RegistrationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity registrationDtoToUserEntity(RegistrationDto registrationDto);

}