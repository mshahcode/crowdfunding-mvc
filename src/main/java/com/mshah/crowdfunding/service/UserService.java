package com.mshah.crowdfunding.service;

import com.mshah.crowdfunding.model.dto.RegistrationDto;

public interface UserService {

    void saveUser(RegistrationDto registrationDto);
}