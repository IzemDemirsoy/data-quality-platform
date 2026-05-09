package com.example.rule_management_service.repository;

import com.example.rule_management_service.model.Rule;
import com.example.rule_management_service.model.RuleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RuleRepository extends JpaRepository<Rule, Long> {

    List<Rule> findByActiveTrue();

    List<Rule> findByFieldNameIgnoreCase(String fieldName);

    List<Rule> findByFieldNameIgnoreCaseAndActiveTrue(String fieldName);

    List<Rule> findByRuleType(RuleType ruleType);
}