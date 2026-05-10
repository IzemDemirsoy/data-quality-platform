package com.example.rule_management_service.dto;

import java.time.LocalDateTime;

public class RuleChangedEvent {

    private String eventType;
    private Long ruleId;
    private String fieldName;
    private String ruleType;
    private boolean active;
    private LocalDateTime eventTime;

    public RuleChangedEvent() {
    }

    public RuleChangedEvent(String eventType, Long ruleId, String fieldName,
                            String ruleType, boolean active, LocalDateTime eventTime) {
        this.eventType = eventType;
        this.ruleId = ruleId;
        this.fieldName = fieldName;
        this.ruleType = ruleType;
        this.active = active;
        this.eventTime = eventTime;
    }

    public String getEventType() {
        return eventType;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getRuleType() {
        return ruleType;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }
}