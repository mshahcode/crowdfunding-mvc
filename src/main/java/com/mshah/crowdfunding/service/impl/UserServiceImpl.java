package com.mshah.crowdfunding.service.impl;

import com.mshah.crowdfunding.dao.entity.RoleEntity;
import com.mshah.crowdfunding.dao.repository.UserRepository;
import com.mshah.crowdfunding.mapper.user.UserMapper;
import com.mshah.crowdfunding.model.dto.RegistrationDto;
import com.mshah.crowdfunding.model.dto.UserDto;
import com.mshah.crowdfunding.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUpUser(RegistrationDto registrationDto) {
        log.info("UserServiceImpl.signUpUser.start: saving user: {}", registrationDto.getEmail());

        var userEntity = userMapper.registrationDtoToUserEntity(registrationDto, new RoleEntity(), passwordEncoder);

        userRepository.save(userEntity);

        log.info("UserServiceImpl.signUpUser.end: saved user: {}", registrationDto.getEmail());
    }

    @Override
    public List<UserDto> getAllUsers() {
        log.info("UserServiceImpl.getAllUsers.start: fetching all users");

        var users = userRepository.findAllUsersWithRoles().stream()
                .map(userMapper::userEntityToUserDto)
                .toList();

        log.info("UserServiceImpl.getAllUsers.end: fetched all users");

        return users;
    }
}