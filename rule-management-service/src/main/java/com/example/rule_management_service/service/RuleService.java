package com.example.rule_management_service.service;

import com.example.rule_management_service.dto.RuleRequestDto;
import com.example.rule_management_service.dto.RuleResponseDto;
import com.example.rule_management_service.exception.RuleNotFoundException;
import com.example.rule_management_service.model.Rule;
import com.example.rule_management_service.model.RuleType;
import com.example.rule_management_service.repository.RuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleService {

    private final RuleRepository ruleRepository;
    private final RuleEventPublisher ruleEventPublisher;

    public RuleService(RuleRepository ruleRepository, RuleEventPublisher ruleEventPublisher) {
        this.ruleRepository = ruleRepository;
        this.ruleEventPublisher = ruleEventPublisher;
    }

    public RuleResponseDto createRule(RuleRequestDto request) {
        Rule rule = new Rule(
                null,
                request.getFieldName(),
                request.getRuleType(),
                request.getRuleValue(),
                request.getErrorMessage(),
                request.isActive()
        );

        Rule savedRule = ruleRepository.save(rule);

        ruleEventPublisher.publishRuleChangedEvent("RULE_CREATED", savedRule);

        return mapToResponse(savedRule);
    }

    public List<RuleResponseDto> getAllRules() {
        return ruleRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public RuleResponseDto getRuleById(Long id) {
        Rule rule = findRuleById(id);
        return mapToResponse(rule);
    }

    public RuleResponseDto updateRule(Long id, RuleRequestDto request) {
        Rule rule = findRuleById(id);

        rule.setFieldName(request.getFieldName());
        rule.setRuleType(request.getRuleType());
        rule.setRuleValue(request.getRuleValue());
        rule.setErrorMessage(request.getErrorMessage());
        rule.setActive(request.isActive());

        Rule updatedRule = ruleRepository.save(rule);

        ruleEventPublisher.publishRuleChangedEvent("RULE_UPDATED", updatedRule);

        return mapToResponse(updatedRule);
    }

    public void deleteRule(Long id) {
        Rule rule = findRuleById(id);

        ruleRepository.delete(rule);

        ruleEventPublisher.publishRuleChangedEvent("RULE_DELETED", rule);
    }

    public RuleResponseDto toggleRuleStatus(Long id) {
        Rule rule = findRuleById(id);

        rule.setActive(!rule.isActive());

        Rule updatedRule = ruleRepository.save(rule);

        ruleEventPublisher.publishRuleChangedEvent("RULE_STATUS_CHANGED", updatedRule);

        return mapToResponse(updatedRule);
    }

    public List<RuleResponseDto> getActiveRules() {
        return ruleRepository.findByActiveTrue()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<RuleResponseDto> getRulesByFieldName(String fieldName) {
        return ruleRepository.findByFieldNameIgnoreCase(fieldName)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<RuleResponseDto> getActiveRulesByFieldName(String fieldName) {
        return ruleRepository.findByFieldNameIgnoreCaseAndActiveTrue(fieldName)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<RuleResponseDto> getRulesByType(String ruleType) {
        RuleType type;

        try {
            type = RuleType.valueOf(ruleType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid rule type: " + ruleType);
        }

        return ruleRepository.findByRuleType(type)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private Rule findRuleById(Long id) {
        return ruleRepository.findById(id)
                .orElseThrow(() -> new RuleNotFoundException("Rule not found with id: " + id));
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
}