package com.mshah.crowdfunding.service.strategy.reports;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class ReportGeneratorFactory {
    private final Map<String, ReportStrategy> reportStrategies;


    public ReportStrategy getReportStrategy(String reportType) {
        System.out.println("ReportGeneratorFactory.getReportStrategy reportType: " + reportType);
        System.out.println("ReportGeneratorFactory.getReportStrategy reportType: " + reportStrategies);
        var generator = reportStrategies.get(reportType);
        if (generator == null) {
            throw new IllegalArgumentException("Report type not supported: " + reportType);
        }
        return generator;
    }
}
