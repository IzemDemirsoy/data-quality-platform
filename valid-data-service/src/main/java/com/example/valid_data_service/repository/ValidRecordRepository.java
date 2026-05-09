package com.example.valid_data_service.repository;

import com.example.valid_data_service.model.ValidRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ValidRecordRepository extends JpaRepository<ValidRecord, Long> {

    List<ValidRecord> findByRecordId(String recordId);

    List<ValidRecord> findBySource(String source);
}