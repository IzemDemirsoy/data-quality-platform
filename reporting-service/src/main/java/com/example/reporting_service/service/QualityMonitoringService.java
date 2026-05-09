package com.example.reporting_service.service;

import com.example.reporting_service.dto.QualitySummaryResponse;
import com.example.reporting_service.model.ErrorRecord;
import com.example.reporting_service.repository.ErrorRecordRepository;
import com.example.reporting_service.repository.ValidRecordRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QualityMonitoringService {

    private final ValidRecordRepository validRecordRepository;
    private final ErrorRecordRepository errorRecordRepository;

    public QualityMonitoringService(ValidRecordRepository validRecordRepository,
                                    ErrorRecordRepository errorRecordRepository) {
        this.validRecordRepository = validRecordRepository;
        this.errorRecordRepository = errorRecordRepository;
    }

    public QualitySummaryResponse getSummary() {
        long validCount = validRecordRepository.count();
        long invalidCount = errorRecordRepository.count();
        long total = validCount + invalidCount;

        double errorRate = calculateErrorRate(invalidCount, total);
        double qualityScore = 100.0 - errorRate;
        String riskLevel = calculateRiskLevel(errorRate);

        List<ErrorRecord> errors = errorRecordRepository.findAll();

        Map<String, Long> sourceErrorCounts = calculateSourceErrorCounts(errors);
        List<String> mostCommonErrors = calculateMostCommonErrors(errors);

        String recommendation = generateRecommendation(errorRate, sourceErrorCounts);

        return new QualitySummaryResponse(
                total,
                validCount,
                invalidCount,
                errorRate,
                qualityScore,
                riskLevel,
                sourceErrorCounts,
                mostCommonErrors,
                recommendation
        );
    }

    public double getQualityScore() {
        long validCount = validRecordRepository.count();
        long invalidCount = errorRecordRepository.count();
        long total = validCount + invalidCount;

        return 100.0 - calculateErrorRate(invalidCount, total);
    }

    public String getRiskLevel() {
        long validCount = validRecordRepository.count();
        long invalidCount = errorRecordRepository.count();
        long total = validCount + invalidCount;

        double errorRate = calculateErrorRate(invalidCount, total);
        return calculateRiskLevel(errorRate);
    }

    public Map<String, Long> getSourceAnalysis() {
        List<ErrorRecord> errors = errorRecordRepository.findAll();
        return calculateSourceErrorCounts(errors);
    }

    public List<String> getErrorAnalysis() {
        List<ErrorRecord> errors = errorRecordRepository.findAll();
        return calculateMostCommonErrors(errors);
    }

    private double calculateErrorRate(long invalidCount, long total) {
        if (total == 0) {
            return 0.0;
        }

        return (invalidCount * 100.0) / total;
    }

    private String calculateRiskLevel(double errorRate) {
        if (errorRate < 10) {
            return "LOW";
        } else if (errorRate <= 30) {
            return "MEDIUM";
        } else {
            return "HIGH";
        }
    }

    private Map<String, Long> calculateSourceErrorCounts(List<ErrorRecord> errors) {
        return errors.stream()
                .filter(error -> error.getSource() != null)
                .collect(Collectors.groupingBy(ErrorRecord::getSource, Collectors.counting()));
    }

    private List<String> calculateMostCommonErrors(List<ErrorRecord> errorRecords) {
        Map<String, Long> errorCounts = new HashMap<>();

        for (ErrorRecord record : errorRecords) {
            if (record.getErrors() != null) {
                for (String error : record.getErrors()) {
                    errorCounts.put(error, errorCounts.getOrDefault(error, 0L) + 1);
                }
            }
        }

        return errorCounts.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .map(entry -> entry.getKey() + " (" + entry.getValue() + ")")
                .toList();
    }

    private String generateRecommendation(double errorRate, Map<String, Long> sourceErrorCounts) {
        if (errorRate == 0) {
            return "Data quality is excellent. No invalid records detected.";
        }

        if (errorRate < 10) {
            return "Data quality is good. Continue monitoring incoming data sources.";
        }

        if (errorRate <= 30) {
            return "Data quality should be monitored. Some sources are producing invalid records.";
        }

        String problematicSource = sourceErrorCounts.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("unknown");

        return "High data quality risk detected. Source '" + problematicSource + "' should be reviewed immediately.";
    }
}