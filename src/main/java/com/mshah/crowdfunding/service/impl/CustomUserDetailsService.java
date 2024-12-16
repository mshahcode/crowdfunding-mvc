package com.mshah.crowdfunding.service.impl;


import com.mshah.crowdfunding.dao.entity.UserEntity;
import com.mshah.crowdfunding.dao.repository.UserRepository;
import com.mshah.crowdfunding.model.constant.Constants.ErrorMessages;
import com.mshah.crowdfunding.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
                            log.error("CustomUserDetailsService.loadUserByUsername.error:" +
                                    " User not found with email: {}", email);
                            return new UsernameNotFoundException(
                                    String.format(ErrorMessages.USER_NOT_FOUND_MESSAGE, email));
                        }
                );

        var userDetails = CustomUserDetails.builder()
                .userEntity(userEntity)
                .build();

        log.info("CustomUserDetailsService.loadUserByUsername.end: Loaded user by email: {}", userEntity);

        return userDetails;
    }
}
