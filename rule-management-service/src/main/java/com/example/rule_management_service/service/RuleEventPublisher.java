package com.example.rule_management_service.service;

import com.example.rule_management_service.dto.RuleChangedEvent;
import com.example.rule_management_service.model.Rule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RuleEventPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${app.kafka.topic.rule-changed-events}")
    private String ruleChangedTopic;

    public RuleEventPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishRuleChangedEvent(String eventType, Rule rule) {
        String payload = "{"
                + "\"eventType\":\"" + eventType + "\","
                + "\"ruleId\":" + rule.getId() + ","
                + "\"fieldName\":\"" + rule.getFieldName() + "\","
                + "\"ruleType\":\"" + rule.getRuleType().name() + "\","
                + "\"active\":" + rule.isActive() + ","
                + "\"eventTime\":\"" + LocalDateTime.now() + "\""
                + "}";

        kafkaTemplate.send(ruleChangedTopic, String.valueOf(rule.getId()), payload);

        System.out.println("Rule change event published: " + payload);
    }
}