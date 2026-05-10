package com.example.validation_service.service;

import com.example.validation_service.client.RuleClient;
import com.example.validation_service.model.Rule;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RuleCacheService {

    private final RuleClient ruleClient;
    private List<Rule> activeRules = new ArrayList<>();

    public RuleCacheService(RuleClient ruleClient) {
        this.ruleClient = ruleClient;
        refreshRules();
    }

    public List<Rule> getActiveRules() {
        return activeRules;
    }

    public void refreshRules() {
        this.activeRules = ruleClient.getActiveRules();
        System.out.println("Rule cache refreshed. Active rule count: " + activeRules.size());
    }
}