package com.example.external_user_collector_service.controller;

import com.example.external_user_collector_service.service.ExternalUserCollectorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/external-collector")
public class ExternalUserCollectorController {

    private final ExternalUserCollectorService collectorService;

    public ExternalUserCollectorController(ExternalUserCollectorService collectorService) {
        this.collectorService = collectorService;
    }

    @PostMapping("/fetch-once")
    public ResponseEntity<String> fetchOnce() {
        return ResponseEntity.ok(collectorService.fetchOnce());
    }

    @PostMapping("/start")
    public ResponseEntity<String> start() {
        collectorService.start();
        return ResponseEntity.ok("External user collector started.");
    }

    @PostMapping("/stop")
    public ResponseEntity<String> stop() {
        collectorService.stop();
        return ResponseEntity.ok("External user collector stopped.");
    }

    @GetMapping("/status")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok(
                collectorService.isRunning()
                        ? "External user collector is running."
                        : "External user collector is stopped."
        );
    }
}