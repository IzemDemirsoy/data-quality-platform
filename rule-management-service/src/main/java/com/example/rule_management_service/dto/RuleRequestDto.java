package com.example.rule_management_service.dto;

import com.example.rule_management_service.model.RuleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RuleRequestDto {

    @NotBlank(message = "Field name cannot be blank")
    private String fieldName;

    @NotNull(message = "Rule type cannot be null")
    private RuleType ruleType;

    @NotBlank(message = "Rule value cannot be blank")
    private String ruleValue;

    @NotBlank(message = "Error message cannot be blank")
    private String errorMessage;

    private boolean active;

    public RuleRequestDto() {
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