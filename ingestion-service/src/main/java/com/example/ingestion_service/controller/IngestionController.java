package com.example.ingestion_service.controller;

import com.example.ingestion_service.dto.IncomingRecordRequestDto;
import com.example.ingestion_service.dto.IngestionResponseDto;
import com.example.ingestion_service.service.IngestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ingestion")
public class IngestionController {

    private final IngestionService ingestionService;

    public IngestionController(IngestionService ingestionService) {
        this.ingestionService = ingestionService;
    }

    @PostMapping("/record")
    public ResponseEntity<IngestionResponseDto> ingestRecord(@RequestBody IncomingRecordRequestDto request) {
        return new ResponseEntity<>(ingestionService.ingestRecord(request), HttpStatus.ACCEPTED);
    }

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("Ingestion service is up and running");
    }
}