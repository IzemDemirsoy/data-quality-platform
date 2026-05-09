package com.example.validation_service.client;
import com.example.validation_service.model.Rule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component

public class RuleClient {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${rule.service.url}")
    private String ruleServiceUrl;

    public List<Rule> getActiveRules() {
        Rule[] rules = restTemplate.getForObject(ruleServiceUrl, Rule[].class);
        if (rules == null) {
            return List.of();
        }
        return Arrays.asList(rules);
    }
}
