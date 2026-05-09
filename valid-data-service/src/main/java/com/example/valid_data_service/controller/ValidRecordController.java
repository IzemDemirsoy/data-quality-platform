package com.example.valid_data_service.controller;

import com.example.valid_data_service.model.ValidRecord;
import com.example.valid_data_service.service.ValidRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/valid-records")
public class ValidRecordController {

    private final ValidRecordService validRecordService;

    public ValidRecordController(ValidRecordService validRecordService) {
        this.validRecordService = validRecordService;
    }

    @GetMapping
    public ResponseEntity<List<ValidRecord>> getAllRecords() {
        return ResponseEntity.ok(validRecordService.getAllRecords());
    }

    @GetMapping("/record/{recordId}")
    public ResponseEntity<List<ValidRecord>> getRecordsByRecordId(@PathVariable String recordId) {
        return ResponseEntity.ok(validRecordService.getRecordsByRecordId(recordId));
    }

    @GetMapping("/source/{source}")
    public ResponseEntity<List<ValidRecord>> getRecordsBySource(@PathVariable String source) {
        return ResponseEntity.ok(validRecordService.getRecordsBySource(source));
    }

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("Valid Data Service is up and running");
    }
}