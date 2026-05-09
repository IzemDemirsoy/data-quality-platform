package com.example.reporting_service.controller;

import com.example.reporting_service.dto.QualitySummaryResponse;
import com.example.reporting_service.service.QualityMonitoringService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/quality")
public class QualityMonitoringController {

    private final QualityMonitoringService qualityMonitoringService;

    public QualityMonitoringController(QualityMonitoringService qualityMonitoringService) {
        this.qualityMonitoringService = qualityMonitoringService;
    }

    @GetMapping("/summary")
    public ResponseEntity<QualitySummaryResponse> getSummary() {
        return ResponseEntity.ok(qualityMonitoringService.getSummary());
    }

    @GetMapping("/score")
    public ResponseEntity<Double> getQualityScore() {
        return ResponseEntity.ok(qualityMonitoringService.getQualityScore());
    }

    @GetMapping("/risk-level")
    public ResponseEntity<String> getRiskLevel() {
        return ResponseEntity.ok(qualityMonitoringService.getRiskLevel());
    }

    @GetMapping("/source-analysis")
    public ResponseEntity<Map<String, Long>> getSourceAnalysis() {
        return ResponseEntity.ok(qualityMonitoringService.getSourceAnalysis());
    }

    @GetMapping("/error-analysis")
    public ResponseEntity<List<String>> getErrorAnalysis() {
        return ResponseEntity.ok(qualityMonitoringService.getErrorAnalysis());
    }

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("Data Quality Monitoring Service is up and running");
    }
}