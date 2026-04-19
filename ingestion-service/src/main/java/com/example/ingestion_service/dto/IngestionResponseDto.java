package com.example.ingestion_service.dto;

public class IngestionResponseDto {

    private String message;
    private String recordId;
    private String status;

    public IngestionResponseDto() {
    }

    public IngestionResponseDto(String message, String recordId, String status) {
        this.message = message;
        this.recordId = recordId;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public String getRecordId() {
        return recordId;
    }

    public String getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}