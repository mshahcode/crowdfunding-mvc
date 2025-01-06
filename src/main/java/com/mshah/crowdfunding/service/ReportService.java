package com.mshah.crowdfunding.service;

import com.mshah.crowdfunding.dao.entity.Report;
import com.mshah.crowdfunding.dao.entity.UserEntity;
import com.mshah.crowdfunding.model.dto.NewReportDto;
import org.springframework.core.io.Resource;

import java.util.List;

public interface ReportService {
    void generateReportRequest(NewReportDto reportDto, UserEntity user);

    Resource downloadReport(Long id);

    List<Report> getAllReports();

    void deleteReport(Long id);
}