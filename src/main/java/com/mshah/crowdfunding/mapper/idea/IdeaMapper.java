package com.mshah.crowdfunding.mapper.idea;

import com.mshah.crowdfunding.dao.entity.Idea;
import com.mshah.crowdfunding.model.dto.IdeaCardDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IdeaMapper {

    IdeaCardDto mapToIdeaCardDto(Idea idea);
}