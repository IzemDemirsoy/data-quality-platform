package com.example.rule_management_service.service;

import com.example.rule_management_service.dto.RuleRequestDto;
import com.example.rule_management_service.dto.RuleResponseDto;
import com.example.rule_management_service.model.Rule;
import org.springframework.stereotype.Service;
import com.example.rule_management_service.exception.RuleNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import com.example.rule_management_service.model.RuleType;

@Service
public class RuleService {

    private final List<Rule> rules = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public RuleResponseDto createRule(RuleRequestDto request) {
        Rule rule = new Rule(
                idGenerator.getAndIncrement(),
                request.getFieldName(),
                request.getRuleType(),
                request.getRuleValue(),
                request.getErrorMessage(),
                request.isActive()
        );

        rules.add(rule);
        return mapToResponse(rule);
    }

    public List<RuleResponseDto> getAllRules() {
        return rules.stream()
                .map(this::mapToResponse)
                .toList();
    }

    public RuleResponseDto getRuleById(Long id) {
        Rule rule = rules.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuleNotFoundException("Rule not found with id: " + id));

        return mapToResponse(rule);
    }

    public RuleResponseDto updateRule(Long id, RuleRequestDto request) {
        Rule rule = rules.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuleNotFoundException("Rule not found with id: " + id));

        rule.setFieldName(request.getFieldName());
        rule.setRuleType(request.getRuleType());
        rule.setRuleValue(request.getRuleValue());
        rule.setErrorMessage(request.getErrorMessage());
        rule.setActive(request.isActive());

        return mapToResponse(rule);
    }

    public void deleteRule(Long id) {
        Rule rule = rules.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuleNotFoundException("Rule not found with id: " + id));

        rules.remove(rule);
    }

    public RuleResponseDto toggleRuleStatus(Long id) {
        Rule rule = rules.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuleNotFoundException("Rule not found with id: " + id));

        rule.setActive(!rule.isActive());
        return mapToResponse(rule);
    }

    private RuleResponseDto mapToResponse(Rule rule) {
        return new RuleResponseDto(
                rule.getId(),
                rule.getFieldName(),
                rule.getRuleType(),
                rule.getRuleValue(),
                rule.getErrorMessage(),
                rule.isActive()
        );
    }
    public List<RuleResponseDto> getActiveRules() {
        return rules.stream()
                .filter(Rule::isActive)
                .map(this::mapToResponse)
                .toList();
    }

    public List<RuleResponseDto> getRulesByFieldName(String fieldName) {
        return rules.stream()
                .filter(rule -> rule.getFieldName().equalsIgnoreCase(fieldName))
                .map(this::mapToResponse)
                .toList();
    }

    public List<RuleResponseDto> getActiveRulesByFieldName(String fieldName) {
        return rules.stream()
                .filter(rule -> rule.getFieldName().equalsIgnoreCase(fieldName))
                .filter(Rule::isActive)
                .map(this::mapToResponse)
                .toList();
    }

    public List<RuleResponseDto> getRulesByType(String ruleType) {
        return rules.stream()
                .filter(rule -> rule.getRuleType().name().equalsIgnoreCase(ruleType))
                .map(this::mapToResponse)
                .toList();
    }
}