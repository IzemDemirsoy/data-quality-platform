package com.example.rule_management_service.controller;

import com.example.rule_management_service.dto.RuleRequestDto;
import com.example.rule_management_service.dto.RuleResponseDto;
import com.example.rule_management_service.service.RuleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class RuleController {

    private final RuleService ruleService;

    public RuleController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping
    public ResponseEntity<RuleResponseDto> createRule(@Valid @RequestBody RuleRequestDto request) {
        return new ResponseEntity<>(ruleService.createRule(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RuleResponseDto>> getAllRules() {
        return ResponseEntity.ok(ruleService.getAllRules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RuleResponseDto> getRuleById(@PathVariable Long id) {
        return ResponseEntity.ok(ruleService.getRuleById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RuleResponseDto> updateRule(@PathVariable Long id,
                                                      @Valid @RequestBody RuleRequestDto request) {
        return ResponseEntity.ok(ruleService.updateRule(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRule(@PathVariable Long id) {
        ruleService.deleteRule(id);
        return ResponseEntity.ok("Rule deleted successfully");
    }

    @PatchMapping("/{id}/toggle")
    public ResponseEntity<RuleResponseDto> toggleRuleStatus(@PathVariable Long id) {
        return ResponseEntity.ok(ruleService.toggleRuleStatus(id));
    }
    @GetMapping("/active")
    public ResponseEntity<List<RuleResponseDto>> getActiveRules() {
        return ResponseEntity.ok(ruleService.getActiveRules());
    }

    @GetMapping("/field/{fieldName}")
    public ResponseEntity<List<RuleResponseDto>> getRulesByFieldName(@PathVariable String fieldName) {
        return ResponseEntity.ok(ruleService.getRulesByFieldName(fieldName));
    }

    @GetMapping("/active/field/{fieldName}")
    public ResponseEntity<List<RuleResponseDto>> getActiveRulesByFieldName(@PathVariable String fieldName) {
        return ResponseEntity.ok(ruleService.getActiveRulesByFieldName(fieldName));
    }

    @GetMapping("/type/{ruleType}")
    public ResponseEntity<List<RuleResponseDto>> getRulesByType(@PathVariable String ruleType) {
        return ResponseEntity.ok(ruleService.getRulesByType(ruleType));
    }
}