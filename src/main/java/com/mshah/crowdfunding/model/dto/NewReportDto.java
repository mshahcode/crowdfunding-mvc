package com.mshah.crowdfunding.model.dto;


import com.mshah.crowdfunding.model.enums.ReportType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewReportDto {
    private ReportType reportType;
}