package com.mshah.crowdfunding.mapper.user;

import com.mshah.crowdfunding.dao.entity.RoleEntity;
import com.mshah.crowdfunding.dao.entity.UserEntity;
import com.mshah.crowdfunding.model.dto.RegistrationDto;
import org.mapstruct.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity registrationDtoToUserEntity(RegistrationDto registrationDto);

    default UserEntity registrationDtoToUserEntity(
            RegistrationDto registrationDto,
            RoleEntity role,
            PasswordEncoder passwordEncoder
    ) {
        var user = registrationDtoToUserEntity(registrationDto);
        user.addRole(role);
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        return user;
    }

}