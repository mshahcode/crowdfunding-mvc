package com.mshah.crowdfunding.service.impl;

import com.mshah.crowdfunding.dao.entity.UserEntity;
import com.mshah.crowdfunding.dao.repository.DonationRepository;
import com.mshah.crowdfunding.dao.repository.IdeaRepository;
import com.mshah.crowdfunding.mapper.idea.IdeaMapper;
import com.mshah.crowdfunding.model.dto.IdeaCardDto;
import com.mshah.crowdfunding.model.dto.IdeaDto;
import com.mshah.crowdfunding.model.dto.IdeaFilterDto;
import com.mshah.crowdfunding.model.dto.NewIdeaDto;
import com.mshah.crowdfunding.service.IdeaService;
import com.mshah.crowdfunding.specification.Idea.IdeaSpecification;
import com.mshah.crowdfunding.util.FileHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mshah.crowdfunding.model.enums.IdeaStatus.COMPLETED;

@Slf4j
@Service
@RequiredArgsConstructor
public class IdeaServiceImpl implements IdeaService {

    private final IdeaRepository ideaRepository;
    private final DonationRepository donationRepository;
    private final IdeaMapper ideaMapper;
    private final FileHelper fileHelper;

    @Value("${idea.upload.dir}")
    private String ideaUploadDir;


    @Override
    public List<IdeaCardDto> getAllIdeaCards(IdeaFilterDto filter) {
        log.info("IdeaServiceImpl.getAllIdeaCards.start: fetching all idea cards");

        var ideaSpecification = new IdeaSpecification(filter);

        var ideaCards = ideaRepository.findAll(ideaSpecification).stream().map(ideaMapper::mapToIdeaCardDto).toList();

        log.info("IdeaServiceImpl.getAllIdeaCards.end: fetched all idea cards");

        return ideaCards;
    }

    @Override
    public IdeaDto getIdeaById(Long id) {
        log.info("IdeaServiceImpl.getIdeaById.start: fetching idea by id: {}", id);

        var ideaDto = ideaRepository.findById(id).map(ideaMapper::mapToIdeaDto)
                .orElseThrow(() -> {
                    log.error("IdeaServiceImpl.getIdeaById.error: Idea not found with id: {}", id);
                    return new RuntimeException("Idea not found with id: " + id);
                });

        log.info("IdeaServiceImpl.getIdeaById.end: fetched idea by id: {}", id);
        return ideaDto;
    }

    @Override
    public List<IdeaDto> getIdeasByUser(Integer userId) {
        log.info("IdeaServiceImpl.getIdeasByUser.start: fetching ideas by user with id: {}", userId);

        var ideaDtos = ideaRepository.findByUserId(userId.longValue()).stream().map(ideaMapper::mapToIdeaDto).toList();

        log.info("IdeaServiceImpl.getIdeasByUser.end: fetched ideas by user with id: {}", userId);
        return ideaDtos;
    }

    @Override
    public void createNewIdea(NewIdeaDto newIdeaDto, UserEntity user) {
        log.info("IdeaServiceImpl.createNewIdea.start: creating new idea by user with id: {}", user.getId());

        var idea = ideaMapper.mapToIdeaEntity(newIdeaDto, user);

        try {
            var uploadedFilePath = fileHelper.uploadFile(newIdeaDto.getImage(), ideaUploadDir).orElse(null);
            idea.setImageUrl(uploadedFilePath);
        } catch (Exception e) {
            log.error("IdeaServiceImpl.createNewIdea.error: error while uploading image for idea: {}", idea.getName());
            throw new RuntimeException("Error while uploading image for idea: " + idea.getName(), e);
        }

        ideaRepository.save(idea);

        log.info("IdeaServiceImpl.createNewIdea.end: created new idea by user with id: {}", user.getId());
    }

    @Override
    @Transactional
    public void deleteIdea(Long id) {
        log.info("IdeaServiceImpl.deleteIdea.start: deleting idea by id: {}", id);

        var relatedDonations = donationRepository.findAllByIdeaId(id);
        donationRepository.deleteAll(relatedDonations);

        ideaRepository.deleteById(id);

        log.info("IdeaServiceImpl.deleteIdea.end: deleted idea by id: {}", id);
    }

    @Override
    public long getTotalCompletedIdeasCount() {
        log.info("IdeaServiceImpl.getTotalCompletedIdeasCount.start: fetching total completed ideas count");

        var totalCompletedIdeasCount = ideaRepository.countByStatus(COMPLETED);

        log.info("IdeaServiceImpl.getTotalCompletedIdeasCount.end: fetched total completed ideas count: {}", totalCompletedIdeasCount);

        return totalCompletedIdeasCount;
    }
}