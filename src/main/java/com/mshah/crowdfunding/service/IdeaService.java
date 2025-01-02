package com.mshah.crowdfunding.service;

import com.mshah.crowdfunding.dao.entity.UserEntity;
import com.mshah.crowdfunding.model.dto.IdeaCardDto;
import com.mshah.crowdfunding.model.dto.IdeaDto;
import com.mshah.crowdfunding.model.dto.IdeaFilterDto;
import com.mshah.crowdfunding.model.dto.NewIdeaDto;

import java.util.List;

public interface IdeaService {

    List<IdeaCardDto> getAllIdeaCards(IdeaFilterDto ideaFilterDto);

    IdeaDto getIdeaById(Long id);

    List<IdeaDto> getIdeasByUser(Integer userId);

    void createNewIdea(NewIdeaDto newIdeaDto, UserEntity user);
}