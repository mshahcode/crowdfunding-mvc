package com.mshah.crowdfunding.service.impl;


import com.mshah.crowdfunding.dao.entity.UserEntity;
import com.mshah.crowdfunding.dao.repository.UserRepository;
import com.mshah.crowdfunding.model.constant.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("CustomUserDetailsService.loadUserByUsername.start: Loading user by email: {}", email);
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                            log.error("loadUserByUsername.error: User not found with email: {}", email);
                            return new UsernameNotFoundException(String.format(Constants.USER_NOT_FOUND_MESSAGE, email));
                        }
                );

        var userDetails = User.builder()
                .username(userEntity.getEmail())
                .password(userEntity.getPassword())
                .roles(userEntity.getRoles()
                        .stream()
                        .map(role -> role.getName().name())
                        .toArray(String[]::new))
                .disabled(!userEntity.getIsActive())
                .build();

        log.info("CustomUserDetailsService.loadUserByUsername.end: Loaded user by email: {}", userEntity);

        return userDetails;
    }
}
