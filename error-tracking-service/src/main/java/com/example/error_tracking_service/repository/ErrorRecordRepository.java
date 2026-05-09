package com.example.error_tracking_service.repository;

import com.example.error_tracking_service.model.ErrorRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ErrorRecordRepository extends MongoRepository<ErrorRecord, String> {

    List<ErrorRecord> findByRecordId(String recordId);

    List<ErrorRecord> findBySource(String source);
}