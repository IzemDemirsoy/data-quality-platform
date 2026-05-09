package com.example.validation_service.model;

public class Rule {
    private Long id;
    private String fieldName;
    private String ruleType;
    private String ruleValue;
    private String errorMessage;
    private boolean active;

    public Rule() {}

    public Long getId() { return id; }
    public String getFieldName() { return fieldName; }
    public String getRuleType() { return ruleType; }
    public String getRuleValue() { return ruleValue; }
    public String getErrorMessage() { return errorMessage; }
    public boolean isActive() { return active; }

    public void setId(Long id) { this.id = id; }
    public void setFieldName(String fieldName) { this.fieldName = fieldName; }
    public void setRuleType(String ruleType) { this.ruleType = ruleType; }
    public void setRuleValue(String ruleValue) { this.ruleValue = ruleValue; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
    public void setActive(boolean active) { this.active = active; }
}
