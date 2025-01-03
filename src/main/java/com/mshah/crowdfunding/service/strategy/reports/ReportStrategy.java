package com.mshah.crowdfunding.service.strategy.reports;

import com.mshah.crowdfunding.dao.entity.Report;

public interface ReportStrategy {
    String generateCsvReport(Report report);
}