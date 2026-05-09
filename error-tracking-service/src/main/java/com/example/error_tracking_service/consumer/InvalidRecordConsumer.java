package com.example.error_tracking_service.consumer;

import com.example.error_tracking_service.model.ErrorRecord;
import com.example.error_tracking_service.service.ErrorTrackingService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InvalidRecordConsumer {

    private final ErrorTrackingService errorTrackingService;

    public InvalidRecordConsumer(ErrorTrackingService errorTrackingService) {
        this.errorTrackingService = errorTrackingService;
    }

    @KafkaListener(
            topics = "${app.kafka.topic.invalid-records}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(String message) {

        try {

            System.out.println("Invalid record received: " + message);

            String recordId = extractValue(message, "recordId");
            String source = extractValue(message, "source");

            List<String> errors = new ArrayList<>();

            if (message.contains("Invalid email format")) {
                errors.add("Invalid email format");
            }

            if (message.contains("Age cannot be negative")) {
                errors.add("Age cannot be negative");
            }

            Map<String, Object> originalRecord = new HashMap<>();
            originalRecord.put("rawMessage", message);

            ErrorRecord errorRecord = new ErrorRecord(
                    recordId,
                    source,
                    originalRecord,
                    errors,
                    LocalDateTime.now()
            );

            errorTrackingService.save(errorRecord);

            System.out.println("Error record saved to MongoDB: " + recordId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String extractValue(String json, String fieldName) {

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
}