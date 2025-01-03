package com.mshah.crowdfunding.service.impl;


import com.mshah.crowdfunding.dao.entity.Report;
import com.mshah.crowdfunding.dao.entity.UserEntity;
import com.mshah.crowdfunding.dao.repository.ReportRepository;
import com.mshah.crowdfunding.model.dto.NewReportDto;
import com.mshah.crowdfunding.model.enums.ReportStatus;
import com.mshah.crowdfunding.service.ReportService;
import com.mshah.crowdfunding.service.strategy.reports.ReportGeneratorFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.mshah.crowdfunding.model.enums.ReportStatus.IN_PROGRESS;

@Slf4j
@Service
@RequiredArgsConstructor

public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ReportGeneratorFactory reportGeneratorFactory;


    @Override
    @Transactional
    public void generateReportRequest(NewReportDto reportDto, UserEntity user) {
        log.info("ReportServiceImpl.generateReportRequest.start: Creating report request for user with id: {}", user.getId());

        var report = Report.builder()
                .status(IN_PROGRESS)
                .userEntity(user)
                .type(reportDto.getReportType())
                .build();

        reportRepository.save(report);

        CompletableFuture.runAsync(() -> generateCsvReport(report));

        log.info("ReportServiceImpl.generateReportRequest.end: Report request created for user with id: {}", user.getId());
    }

    private void generateCsvReport(Report report) {
        log.info("ReportServiceImpl.generateCSV.start: Generating report {}", report);

        try {
            var reportGenerator = reportGeneratorFactory.getReportStrategy(report.getType().name());
            var fileName = reportGenerator.generateCsvReport(report);
            report.setFileName(fileName);
            report.setStatus(ReportStatus.COMPLETED);
            reportRepository.save(report);

            log.info("ReportServiceImpl.generateCSV.end: Report generated");
        } catch (Exception e) {
            log.error("ReportServiceImpl.generateCSV.error:", e);
            report.setStatus(ReportStatus.ERROR);
            reportRepository.save(report);
        }
    }

    @Override
    public Resource downloadReport(Long id) {
        log.info("ReportServiceImpl.downloadReport.start: Downloading report with id: {}", id);

        var report = reportRepository.findById(id).orElseThrow(() -> {
            log.error("ReportServiceImpl.downloadReport.error: Report not found with id: {}", id);
            return new RuntimeException("Report not found with id: " + id);
        });

        var file = new File("tmp/reports/" + report.getFileName());

        if (file.exists()) {
            return new FileSystemResource(file);
        } else {
            log.error("ReportServiceImpl.downloadReport.error: File not found");
            throw new RuntimeException("File not found");
        }
    }

    @Override
    public List<Report> getAllReports() {
        log.info("ReportServiceImpl.getAllReports.start: Fetching all reports");
        var reports = reportRepository.findAllByOrderByUpdatedAtDesc();
        log.info("ReportServiceImpl.getAllReports.end: Fetched all reports");
        return reports;
    }
}
