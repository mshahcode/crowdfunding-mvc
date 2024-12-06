package com.mshah.crowdfunding.service.impl;

import com.mshah.crowdfunding.dao.repository.UserRepository;
import com.mshah.crowdfunding.mapper.user.UserMapper;
import com.mshah.crowdfunding.model.dto.RegistrationDto;
import com.mshah.crowdfunding.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        log.info("UserServiceImpl.saveUser.start: saving user: " + registrationDto.getEmail());

        registrationDto.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

        userRepository.save(userMapper.registrationDtoToUserEntity(registrationDto));

        log.info("UserServiceImpl.saveUser.end: saved user: " + registrationDto.getEmail());
    }
}