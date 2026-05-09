package com.example.validation_service.model;
import java.util.List;

public class InvalidRecord {
    private IncomingRecord originalRecord;
    private List<String> errors;

    public InvalidRecord() {}

    public InvalidRecord(IncomingRecord originalRecord, List<String> errors) {
        this.originalRecord = originalRecord;
        this.errors = errors;
    }

    public IncomingRecord getOriginalRecord() { return originalRecord; }
    public void setOriginalRecord(IncomingRecord originalRecord) { this.originalRecord = originalRecord; }

    public List<String> getErrors() { return errors; }
    public void setErrors(List<String> errors) { this.errors = errors; }
}
