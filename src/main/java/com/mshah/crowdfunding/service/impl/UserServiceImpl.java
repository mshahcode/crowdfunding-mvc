package com.mshah.crowdfunding.service.impl;

import com.mshah.crowdfunding.dao.entity.RoleEntity;
import com.mshah.crowdfunding.dao.repository.UserRepository;
import com.mshah.crowdfunding.mapper.user.UserMapper;
import com.mshah.crowdfunding.model.dto.RegistrationDto;
import com.mshah.crowdfunding.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        log.info("UserServiceImpl.saveUser.start: saving user: {}", registrationDto.getEmail());

        var userEntity = userMapper.registrationDtoToUserEntity(registrationDto, new RoleEntity(), passwordEncoder);

        userRepository.save(userEntity);

        log.info("UserServiceImpl.saveUser.end: saved user: {}", registrationDto.getEmail());
    }
}