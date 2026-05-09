package com.example.validation_service.consumer;
import com.example.validation_service.client.RuleClient;
import com.example.validation_service.model.IncomingRecord;
import com.example.validation_service.model.InvalidRecord;
import com.example.validation_service.model.Rule;
import com.example.validation_service.service.ValidationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecordConsumer {
    private final RuleClient ruleClient;
    private final ValidationService validationService;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${app.kafka.topic.valid-records}")
    private String validRecordsTopic;

    @Value("${app.kafka.topic.invalid-records}")
    private String invalidRecordsTopic;

    public RecordConsumer(RuleClient ruleClient,
                          ValidationService validationService,
                          KafkaTemplate<String, String> kafkaTemplate) {
        this.ruleClient = ruleClient;
        this.validationService = validationService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "${app.kafka.topic.incoming-records}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message) {
        try {
            IncomingRecord record = objectMapper.readValue(message, IncomingRecord.class);

            List<Rule> rules = ruleClient.getActiveRules();
            List<String> errors = validationService.validate(record, rules);

            if (errors.isEmpty()) {
                kafkaTemplate.send(validRecordsTopic, record.getRecordId(), message);
                System.out.println("Valid record sent: " + record.getRecordId());
            } else {
                InvalidRecord invalidRecord = new InvalidRecord(record, errors);
                String invalidPayload = objectMapper.writeValueAsString(invalidRecord);
                kafkaTemplate.send(invalidRecordsTopic, record.getRecordId(), invalidPayload);
                System.out.println("Invalid record sent: " + record.getRecordId());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
