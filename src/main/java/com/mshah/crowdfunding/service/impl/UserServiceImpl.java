package com.mshah.crowdfunding.service.impl;

import com.mshah.crowdfunding.dao.entity.RoleEntity;
import com.mshah.crowdfunding.dao.repository.DonationRepository;
import com.mshah.crowdfunding.dao.repository.IdeaRepository;
import com.mshah.crowdfunding.dao.repository.ReportRepository;
import com.mshah.crowdfunding.dao.repository.UserRepository;
import com.mshah.crowdfunding.mapper.user.UserMapper;
import com.mshah.crowdfunding.model.dto.RegistrationDto;
import com.mshah.crowdfunding.model.dto.UserDto;
import com.mshah.crowdfunding.service.UserService;
import jakarta.transaction.Transactional;
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
    private final DonationRepository donationRepository;
    private final ReportRepository reportRepository;
    private final IdeaRepository ideaRepository;
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

    @Override
    public long getTotalUsersCount() {
        log.info("UserServiceImpl.getTotalUsersCount.start: fetching total users count");

        var totalUsersCount = userRepository.count();

        log.info("UserServiceImpl.getTotalUsersCount.end: fetched total users count: {}", totalUsersCount);

        return totalUsersCount;
    }

    @Override
    public long getTotalActiveUsersCount() {
        log.info("UserServiceImpl.getTotalActiveUsersCount.start: fetching total active users count");

        var totalActiveUsersCount = userRepository.countByIsActive(true);

        log.info("UserServiceImpl.getTotalActiveUsersCount.end: fetched total active users count: {}", totalActiveUsersCount);
        return totalActiveUsersCount;
    }

    @Override
    public void activateUser(Long id) {
        log.info("UserServiceImpl.activateUser.start: activating user with id: {}", id);

        var user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setIsActive(true);

        userRepository.save(user);

        log.info("UserServiceImpl.activateUser.end: activated user with id: {}", id);
    }

    @Override
    public void deActivateUser(Long id) {
        log.info("UserServiceImpl.deActivateUser.start: deactivating user with id: {}", id);

        var user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setIsActive(false);

        userRepository.save(user);

        log.info("UserServiceImpl.deActivateUser.end: deactivated user with id: {}", id);

    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        log.info("UserServiceImpl.deleteUser.start: deleting user with id: {}", id);

        try {
            var relatedDonations = donationRepository.findAllByUserId(id);
            donationRepository.deleteAll(relatedDonations);

            var relatedReports = reportRepository.findAllByUserId(id);
            reportRepository.deleteAll(relatedReports);

            var relatedIdeas = ideaRepository.findByUserId(id);
            ideaRepository.deleteAll(relatedIdeas);

            userRepository.deleteById(id);
        } catch (Exception e) {
            log.error("UserServiceImpl.deleteUser.error: error while deleting user with id: {}", id);
            throw new RuntimeException("Error while deleting user with id: " + id, e);
        }

        log.info("UserServiceImpl.deleteUser.end: deleted user with id: {}", id);
    }

}