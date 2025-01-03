package com.mshah.crowdfunding.service;

import com.mshah.crowdfunding.model.dto.RegistrationDto;
import com.mshah.crowdfunding.model.dto.UserDto;

import java.util.List;

public interface UserService {

    void signUpUser(RegistrationDto registrationDto);

    List<UserDto> getAllUsers();
}