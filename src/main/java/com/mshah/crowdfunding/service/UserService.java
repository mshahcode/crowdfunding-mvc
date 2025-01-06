package com.mshah.crowdfunding.service;

import com.mshah.crowdfunding.model.dto.RegistrationDto;
import com.mshah.crowdfunding.model.dto.UserDto;

import java.util.List;

public interface UserService {

    void signUpUser(RegistrationDto registrationDto);

    List<UserDto> getAllUsers();
    
    long getTotalUsersCount();

    long getTotalActiveUsersCount();

    void activateUser(Long id);

    void deActivateUser(Long id);

    void deleteUser(Long id);

    boolean isEmailPresent(String email);
}