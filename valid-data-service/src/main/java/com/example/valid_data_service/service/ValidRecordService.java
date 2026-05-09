package com.example.valid_data_service.service;

import com.example.valid_data_service.model.ValidRecord;
import com.example.valid_data_service.repository.ValidRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidRecordService {

    private final ValidRecordRepository validRecordRepository;

    public ValidRecordService(ValidRecordRepository validRecordRepository) {
        this.validRecordRepository = validRecordRepository;
    }

    public ValidRecord save(ValidRecord validRecord) {
        return validRecordRepository.save(validRecord);
    }

    public List<ValidRecord> getAllRecords() {
        return validRecordRepository.findAll();
    }

    public List<ValidRecord> getRecordsByRecordId(String recordId) {
        return validRecordRepository.findByRecordId(recordId);
    }

    public List<ValidRecord> getRecordsBySource(String source) {
        return validRecordRepository.findBySource(source);
    }
}