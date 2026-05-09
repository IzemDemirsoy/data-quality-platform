package com.example.validation_service.service;

import com.example.validation_service.model.IncomingRecord;
import com.example.validation_service.model.Rule;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValidationService {
    public List<String> validate(IncomingRecord record, List<Rule> rules) {
        List<String> errors = new ArrayList<>();

        for (Rule rule : rules) {
            String fieldValue = getFieldValue(record, rule.getFieldName());

            switch (rule.getRuleType()) {
                case "NOT_NULL":
                    if (fieldValue == null || fieldValue.isBlank()) {
                        errors.add(rule.getErrorMessage());
                    }
                    break;

                case "REGEX":
                    if (fieldValue == null || !fieldValue.matches(rule.getRuleValue())) {
                        errors.add(rule.getErrorMessage());
                    }
                    break;

                case "MIN":
                    try {
                        int value = Integer.parseInt(fieldValue);
                        int min = Integer.parseInt(rule.getRuleValue());
                        if (value < min) {
                            errors.add(rule.getErrorMessage());
                        }
                    } catch (Exception e) {
                        errors.add(rule.getErrorMessage());
                    }
                    break;

                case "LENGTH":
                    if (fieldValue == null || fieldValue.length() != Integer.parseInt(rule.getRuleValue())) {
                        errors.add(rule.getErrorMessage());
                    }
                    break;
            }
        }

        return errors;
    }

    private String getFieldValue(IncomingRecord record, String fieldName) {
        return switch (fieldName) {
            case "recordId" -> record.getRecordId();
            case "customerId" -> record.getCustomerId();
            case "fullName" -> record.getFullName();
            case "email" -> record.getEmail();
            case "phoneNumber" -> record.getPhoneNumber();
            case "age" -> record.getAge() == null ? null : String.valueOf(record.getAge());
            case "city" -> record.getCity();
            case "source" -> record.getSource();
            default -> null;
        };
    }
}
