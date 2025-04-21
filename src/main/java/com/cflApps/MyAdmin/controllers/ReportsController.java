package com.cflApps.MyAdmin.controllers;

import com.cflApps.MyAdmin.dtos.RequestReportDTO;
import com.cflApps.MyAdmin.services.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("reports")
public class ReportsController {

    private final ReportService reportService;

    @Autowired
    public ReportsController(ReportService reportService) {
        this.reportService = reportService;
    }


    @PostMapping(value = "/{reportType}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> exportPDF(@RequestBody RequestReportDTO requestDto, @PathVariable("reportType") String reportType) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=records_report.pdf")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(reportService.generateReport(requestDto, reportType));
    }
}
