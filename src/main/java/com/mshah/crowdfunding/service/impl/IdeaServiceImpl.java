package com.mshah.crowdfunding.service.impl;

import com.mshah.crowdfunding.dao.repository.IdeaRepository;
import com.mshah.crowdfunding.mapper.idea.IdeaMapper;
import com.mshah.crowdfunding.model.dto.IdeaCardDto;
import com.mshah.crowdfunding.model.dto.IdeaDto;
import com.mshah.crowdfunding.model.dto.IdeaFilterDto;
import com.mshah.crowdfunding.service.IdeaService;
import com.mshah.crowdfunding.specification.Idea.IdeaSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class IdeaServiceImpl implements IdeaService {

    private final IdeaRepository ideaRepository;
    private final IdeaMapper ideaMapper;


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
}
