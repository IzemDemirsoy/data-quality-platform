package com.example.rule_management_service.model;

public class Rule {

    private Long id;
    private String fieldName;
    private RuleType ruleType;
    private String ruleValue;
    private String errorMessage;
    private boolean active;

    public Rule() {
    }

    public Rule(Long id, String fieldName, RuleType ruleType, String ruleValue, String errorMessage, boolean active) {
        this.id = id;
        this.fieldName = fieldName;
        this.ruleType = ruleType;
        this.ruleValue = ruleValue;
        this.errorMessage = errorMessage;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getFieldName() {
        return fieldName;
    }

    public RuleType getRuleType() {
        return ruleType;
    }

    public String getRuleValue() {
        return ruleValue;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isActive() {
        return active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public void setRuleType(RuleType ruleType) {
        this.ruleType = ruleType;
    }

    public void setRuleValue(String ruleValue) {
        this.ruleValue = ruleValue;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}