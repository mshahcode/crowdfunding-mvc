package com.mshah.crowdfunding.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdeaFilterDto {
    private String name;
    private String category;
    private String status;
    private String sortBy;
    private String sortOrder;

    public void setName(String name) {
        if (!StringUtils.isBlank(name)) {
            this.name = name;
        }
    }

    public void setCategory(String category) {
        if (!StringUtils.isBlank(category)) {
            this.category = category;
        }
    }

    public void setStatus(String status) {
        if (!StringUtils.isBlank(status)) {
            this.status = status;
        }
    }
}
