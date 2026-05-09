package com.example.error_tracking_service.service;

import com.example.error_tracking_service.model.ErrorRecord;
import com.example.error_tracking_service.repository.ErrorRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ErrorTrackingService {

    private final ErrorRecordRepository errorRecordRepository;

    public ErrorTrackingService(ErrorRecordRepository errorRecordRepository) {
        this.errorRecordRepository = errorRecordRepository;
    }

    public ErrorRecord save(ErrorRecord errorRecord) {
        return errorRecordRepository.save(errorRecord);
    }

    public List<ErrorRecord> getAllErrors() {
        return errorRecordRepository.findAll();
    }

    public List<ErrorRecord> getErrorsByRecordId(String recordId) {
        return errorRecordRepository.findByRecordId(recordId);
    }

    public List<ErrorRecord> getErrorsBySource(String source) {
        return errorRecordRepository.findBySource(source);
    }
}