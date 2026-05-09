package com.example.reporting_service.dto;

import java.util.List;
import java.util.Map;

public class QualitySummaryResponse {

    private long totalProcessedRecords;
    private long validRecords;
    private long invalidRecords;
    private double errorRate;
    private double qualityScore;
    private String riskLevel;
    private Map<String, Long> sourceErrorCounts;
    private List<String> mostCommonErrors;
    private String recommendation;

    public QualitySummaryResponse() {
    }

    public QualitySummaryResponse(long totalProcessedRecords,
                                  long validRecords,
                                  long invalidRecords,
                                  double errorRate,
                                  double qualityScore,
                                  String riskLevel,
                                  Map<String, Long> sourceErrorCounts,
                                  List<String> mostCommonErrors,
                                  String recommendation) {
        this.totalProcessedRecords = totalProcessedRecords;
        this.validRecords = validRecords;
        this.invalidRecords = invalidRecords;
        this.errorRate = errorRate;
        this.qualityScore = qualityScore;
        this.riskLevel = riskLevel;
        this.sourceErrorCounts = sourceErrorCounts;
        this.mostCommonErrors = mostCommonErrors;
        this.recommendation = recommendation;
    }

    public long getTotalProcessedRecords() {
        return totalProcessedRecords;
    }

    public long getValidRecords() {
        return validRecords;
    }

    public long getInvalidRecords() {
        return invalidRecords;
    }

    public double getErrorRate() {
        return errorRate;
    }

    public double getQualityScore() {
        return qualityScore;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public Map<String, Long> getSourceErrorCounts() {
        return sourceErrorCounts;
    }

    public List<String> getMostCommonErrors() {
        return mostCommonErrors;
    }

    public String getRecommendation() {
        return recommendation;
    }
}