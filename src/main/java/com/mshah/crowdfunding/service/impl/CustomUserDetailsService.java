package com.mshah.crowdfunding.service.impl;


import com.mshah.crowdfunding.dao.entity.UserEntity;
import com.mshah.crowdfunding.dao.repository.UserRepository;
import com.mshah.crowdfunding.model.constant.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Log
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("loadUserByUsername.start: Loading user by email: " + email);
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format(Constants.USER_NOT_FOUND_MESSAGE, email)));

        log.info("loadUserByUsername.end: Loaded user: " + userEntity);
        return User.builder()
                .username(userEntity.getEmail())
                .password(userEntity.getPassword())
                .roles(userEntity.getRole().name())
                .disabled(!userEntity.getIsActive())
                .build();
    }
}
