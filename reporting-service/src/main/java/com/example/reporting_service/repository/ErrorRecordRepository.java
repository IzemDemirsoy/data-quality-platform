package com.example.reporting_service.repository;

import com.example.reporting_service.model.ErrorRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ErrorRecordRepository extends MongoRepository<ErrorRecord, String> {

    long countBySource(String source);

    List<ErrorRecord> findBySource(String source);
}