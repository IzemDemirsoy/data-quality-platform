package com.example.reporting_service.repository;

import com.example.reporting_service.model.ValidRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ValidRecordRepository extends JpaRepository<ValidRecord, Long> {

    long countBySource(String source);

    List<ValidRecord> findBySource(String source);
}