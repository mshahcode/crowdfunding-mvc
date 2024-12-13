package com.mshah.crowdfunding.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdeaCardDto {
    private Integer id;
    private String name;
    private String imageUrl;
    private BigDecimal goalAmount;
    private BigDecimal currentAmount;
}