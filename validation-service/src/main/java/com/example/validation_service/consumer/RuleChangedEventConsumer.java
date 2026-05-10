package com.example.validation_service.consumer;

import com.example.validation_service.service.RuleCacheService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class RuleChangedEventConsumer {

    private final RuleCacheService ruleCacheService;

    public RuleChangedEventConsumer(RuleCacheService ruleCacheService) {
        this.ruleCacheService = ruleCacheService;
    }

    @KafkaListener(
            topics = "${app.kafka.topic.rule-changed-events}",
            groupId = "validation-rule-cache-group"
    )
    public void consumeRuleChangedEvent(String message) {
        System.out.println("Rule changed event received: " + message);
        ruleCacheService.refreshRules();
    }
}