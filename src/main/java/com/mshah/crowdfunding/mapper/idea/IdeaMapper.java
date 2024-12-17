package com.mshah.crowdfunding.mapper.idea;

import com.mshah.crowdfunding.dao.entity.Idea;
import com.mshah.crowdfunding.model.dto.IdeaCardDto;
import com.mshah.crowdfunding.model.dto.IdeaDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IdeaMapper {

    IdeaCardDto mapToIdeaCardDto(Idea idea);

    @Mapping(target = "owner", source = "user.nickname")
    IdeaDto mapToIdeaDto(Idea idea);
}