package com.mshah.crowdfunding.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IdeaDto {
    private String id;

    private String name;

    private String description;

    private BigDecimal goalAmount;

    private BigDecimal currentAmount;

    private String status;

    private Instant createdAt;

    private String donationsCount;

    private String imageUrl;

    private String owner;

    private String category;
}
