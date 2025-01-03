package com.mshah.crowdfunding.controller;


import com.mshah.crowdfunding.model.dto.NewReportDto;
import com.mshah.crowdfunding.security.CustomUserDetails;
import com.mshah.crowdfunding.service.impl.ReportServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/reports")
@PreAuthorize("hasRole('ADMIN')")
public class ReportController {
    private final ReportServiceImpl reportService;

    @PostMapping("/generate")
    public String generateReport(@ModelAttribute NewReportDto reportDto, @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        reportService.generateReportRequest(reportDto, customUserDetails.getUserEntity());

        return "redirect:/v1/admin/panel";
    }

    @GetMapping("/{id}/download")
    @ResponseBody
    public ResponseEntity<Resource> downloadReport(@PathVariable Long id) {
        var report = reportService.downloadReport(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + report.getFilename() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, "text/csv")
                .body(report);
    }
}
