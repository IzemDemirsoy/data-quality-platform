package com.example.error_tracking_service.controller;

import com.example.error_tracking_service.model.ErrorRecord;
import com.example.error_tracking_service.service.ErrorTrackingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/errors")
public class ErrorRecordController {

    private final ErrorTrackingService errorTrackingService;

    public ErrorRecordController(ErrorTrackingService errorTrackingService) {
        this.errorTrackingService = errorTrackingService;
    }

    @GetMapping
    public ResponseEntity<List<ErrorRecord>> getAllErrors() {
        return ResponseEntity.ok(errorTrackingService.getAllErrors());
    }

    @GetMapping("/record/{recordId}")
    public ResponseEntity<List<ErrorRecord>> getErrorsByRecordId(@PathVariable String recordId) {
        return ResponseEntity.ok(errorTrackingService.getErrorsByRecordId(recordId));
    }

    @GetMapping("/source/{source}")
    public ResponseEntity<List<ErrorRecord>> getErrorsBySource(@PathVariable String source) {
        return ResponseEntity.ok(errorTrackingService.getErrorsBySource(source));
    }

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("Error Tracking Service is up and running");
    }
}