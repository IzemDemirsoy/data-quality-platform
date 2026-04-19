package com.example.ingestion_service.service;

import com.example.ingestion_service.dto.IncomingRecordRequestDto;
import com.example.ingestion_service.dto.IngestionResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class IngestionService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${app.kafka.topic.incoming-records}")
    private String incomingRecordsTopic;

    public IngestionService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public IngestionResponseDto ingestRecord(IncomingRecordRequestDto request) {
        try {
            String payload = buildJsonPayload(request);

            kafkaTemplate.send(incomingRecordsTopic, request.getRecordId(), payload);

            return new IngestionResponseDto(
                    "Record received and sent to Kafka successfully",
                    request.getRecordId(),
                    "SENT_TO_KAFKA"
            );
        } catch (Exception e) {
            e.printStackTrace();

            return new IngestionResponseDto(
                    "Record received but Kafka send failed: " + e.getMessage(),
                    request.getRecordId(),
                    "KAFKA_SEND_FAILED"
            );
        }
    }

    private String buildJsonPayload(IncomingRecordRequestDto request) {
        return "{"
                + "\"recordId\":\"" + request.getRecordId() + "\","
                + "\"customerId\":\"" + request.getCustomerId() + "\","
                + "\"fullName\":\"" + request.getFullName() + "\","
                + "\"email\":\"" + request.getEmail() + "\","
                + "\"phoneNumber\":\"" + request.getPhoneNumber() + "\","
                + "\"age\":" + request.getAge() + ","
                + "\"city\":\"" + request.getCity() + "\","
                + "\"source\":\"" + request.getSource() + "\""
                + "}";
    }
}