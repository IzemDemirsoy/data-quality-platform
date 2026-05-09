package com.example.valid_data_service.consumer;

import com.example.valid_data_service.model.ValidRecord;
import com.example.valid_data_service.service.ValidRecordService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidRecordConsumer {

    private final ValidRecordService validRecordService;

    public ValidRecordConsumer(ValidRecordService validRecordService) {
        this.validRecordService = validRecordService;
    }

    @KafkaListener(
            topics = "${app.kafka.topic.valid-records}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(String message) {
        try {
            System.out.println("Valid record received: " + message);

            ValidRecord validRecord = new ValidRecord(
                    extractStringValue(message, "recordId"),
                    extractStringValue(message, "customerId"),
                    extractStringValue(message, "fullName"),
                    extractStringValue(message, "email"),
                    extractStringValue(message, "phoneNumber"),
                    extractIntegerValue(message, "age"),
                    extractStringValue(message, "city"),
                    extractStringValue(message, "source"),
                    LocalDateTime.now()
            );

            validRecordService.save(validRecord);

            System.out.println("Valid record saved to MySQL: " + validRecord.getRecordId());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String extractStringValue(String json, String fieldName) {
        try {
            String search = "\"" + fieldName + "\":\"";
            int startIndex = json.indexOf(search);

            if (startIndex == -1) {
                return null;
            }

            startIndex += search.length();
            int endIndex = json.indexOf("\"", startIndex);

            return json.substring(startIndex, endIndex);

        } catch (Exception e) {
            return null;
        }
    }

    private Integer extractIntegerValue(String json, String fieldName) {
        try {
            String search = "\"" + fieldName + "\":";
            int startIndex = json.indexOf(search);

            if (startIndex == -1) {
                return null;
            }

            startIndex += search.length();

            int endIndex = json.indexOf(",", startIndex);
            if (endIndex == -1) {
                endIndex = json.indexOf("}", startIndex);
            }

            String value = json.substring(startIndex, endIndex).trim();
            return Integer.parseInt(value);

        } catch (Exception e) {
            return null;
        }
    }
}