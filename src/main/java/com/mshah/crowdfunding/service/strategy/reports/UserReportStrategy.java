package com.mshah.crowdfunding.service.strategy.reports;

import com.mshah.crowdfunding.dao.entity.Report;
import com.mshah.crowdfunding.dao.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
@Component("USERS_INFO")
@RequiredArgsConstructor
public class UserReportStrategy implements ReportStrategy {

    private final UserRepository userRepository;

    private final String csvRow = "%s,%s,%s,%s\n";

    @Override
    public String generateCsvReport(Report report) {
        try {
            Files.createDirectories(Paths.get("tmp/reports"));

            StringBuilder csvBuilder = new StringBuilder();

            csvBuilder.append("id,nickname,email,is_active\n");

            userRepository.findAll().forEach(user -> {
                var row = String.format(csvRow, user.getId(), user.getNickname(), user.getEmail(), user.getIsActive());
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