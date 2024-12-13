package com.mshah.crowdfunding.service;

import com.mshah.crowdfunding.model.dto.IdeaCardDto;
import com.mshah.crowdfunding.model.dto.IdeaFilterDto;

import java.util.List;

public interface IdeaService {

    List<IdeaCardDto> getAllIdeaCards(IdeaFilterDto ideaFilterDto);
}