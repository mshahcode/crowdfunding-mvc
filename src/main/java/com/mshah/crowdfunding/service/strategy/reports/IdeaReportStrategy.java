package com.mshah.crowdfunding.service.strategy.reports;

import com.mshah.crowdfunding.dao.entity.Report;
import com.mshah.crowdfunding.dao.repository.IdeaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
@Component("IDEAS_INFO")
@RequiredArgsConstructor
public class IdeaReportStrategy implements ReportStrategy {

    private final IdeaRepository ideaRepository;

    private final String csvRow = "%s,%s,%s,%s,%s,%s,%s,%s,%s\n";

    @Override
    public String generateCsvReport(Report report) {
        try {
            Files.createDirectories(Paths.get("tmp/reports"));

            StringBuilder csvBuilder = new StringBuilder();

            csvBuilder.append("id,name,status,category,current_amount,goal_amount,donations_count,created_at,updated_at\n");

            ideaRepository.findAll().forEach(idea -> {
                var row = String.format(csvRow, idea.getId(), idea.getName(), idea.getStatus(), idea.getCategory(), idea.getCurrentAmount(), idea.getGoalAmount(), idea.getDonationsCount(), idea.getCreatedAt(), idea.getUpdatedAt());
                csvBuilder.append(row);
            });

            String result = csvBuilder.toString();

            var name = "report_" + report.getId() + ".csv";

            File file = new File("tmp/reports/" + name);

            try (var writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(result);
            }

            log.info("Report generated: {}", name);
            return name;
        } catch (Exception e) {
            throw new RuntimeException("Error generating report", e);
        }

    }
}