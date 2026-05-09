package com.example.error_tracking_service.model;

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

    public ErrorRecord() {
    }

    public ErrorRecord(String recordId, String source, Map<String, Object> originalRecord, List<String> errors, LocalDateTime createdAt) {
        this.recordId = recordId;
        this.source = source;
        this.originalRecord = originalRecord;
        this.errors = errors;
        this.createdAt = createdAt;
    }

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

    public void setId(String id) {
        this.id = id;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setOriginalRecord(Map<String, Object> originalRecord) {
        this.originalRecord = originalRecord;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}