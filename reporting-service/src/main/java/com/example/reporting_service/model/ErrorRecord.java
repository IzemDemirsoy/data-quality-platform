package com.example.reporting_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Document(collection = "error_records")
public class ErrorRecord {

    @Id
    private String id;

    private String recordId;
    private String source;
    private Map<String, Object> originalRecord;
    private List<String> errors;
    private LocalDateTime createdAt;

    public String getId() {
        return id;
    }

    public String getRecordId() {
        return recordId;
    }

    public String getSource() {
        return source;
    }

    public Map<String, Object> getOriginalRecord() {
        return originalRecord;
    }

    public List<String> getErrors() {
        return errors;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}