package com.mshah.crowdfunding.service.impl;

import com.mshah.crowdfunding.dao.repository.IdeaRepository;
import com.mshah.crowdfunding.mapper.idea.IdeaMapper;
import com.mshah.crowdfunding.model.dto.IdeaCardDto;
import com.mshah.crowdfunding.model.dto.IdeaFilterDto;
import com.mshah.crowdfunding.service.IdeaService;
import com.mshah.crowdfunding.specification.Idea.IdeaSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Log
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
}
