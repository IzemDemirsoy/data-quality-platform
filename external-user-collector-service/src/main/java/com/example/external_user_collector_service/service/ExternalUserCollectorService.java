package com.example.external_user_collector_service.service;

import com.example.external_user_collector_service.dto.IncomingRecordDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class ExternalUserCollectorService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final Random random = new Random();
    private final AtomicBoolean running = new AtomicBoolean(false);

    @Value("${randomuser.api.url}")
    private String randomUserApiUrl;

    @Value("${ingestion.service.url}")
    private String ingestionServiceUrl;

    public String fetchOnce() {
        IncomingRecordDto record = fetchAndTransformUser();
        sendToIngestion(record);

        return "Fetched external user and sent to ingestion. recordId=" + record.getRecordId();
    }

    public void start() {
        running.set(true);
    }

    public void stop() {
        running.set(false);
    }

    public boolean isRunning() {
        return running.get();
    }

    @Scheduled(fixedRateString = "${collector.fixed-rate}")
    public void scheduledFetch() {
        if (!running.get()) {
            return;
        }

        try {
            fetchOnce();
        } catch (Exception e) {
            System.out.println("External collector failed: " + e.getMessage());
        }
    }

    private IncomingRecordDto fetchAndTransformUser() {
        ResponseEntity<Map> response = restTemplate.getForEntity(randomUserApiUrl, Map.class);

        Map<String, Object> body = response.getBody();
        if (body == null) {
            throw new RuntimeException("RandomUser API returned empty body");
        }

        List<Map<String, Object>> results = (List<Map<String, Object>>) body.get("results");
        if (results == null || results.isEmpty()) {
            throw new RuntimeException("RandomUser API returned no results");
        }

        Map<String, Object> user = results.get(0);

        Map<String, Object> login = (Map<String, Object>) user.get("login");
        Map<String, Object> name = (Map<String, Object>) user.get("name");
        Map<String, Object> location = (Map<String, Object>) user.get("location");
        Map<String, Object> dob = (Map<String, Object>) user.get("dob");

        String uuid = String.valueOf(login.get("uuid"));
        String firstName = String.valueOf(name.get("first"));
        String lastName = String.valueOf(name.get("last"));
        String fullName = firstName + " " + lastName;

        String email = String.valueOf(user.get("email"));
        String phone = String.valueOf(user.get("phone"));
        Integer age = Integer.valueOf(String.valueOf(dob.get("age")));
        String city = String.valueOf(location.get("city"));

        IncomingRecordDto record = new IncomingRecordDto(
                uuid,
                uuid,
                fullName,
                email,
                phone,
                age,
                city,
                "external-randomuser-api"
        );

        return corruptSomeRecords(record);
    }

    private IncomingRecordDto corruptSomeRecords(IncomingRecordDto record) {
        int scenario = random.nextInt(100);

        if (scenario < 70) {
            return record;
        }

        if (scenario < 85) {
            record.setEmail("wrong-email");
            return record;
        }

        if (scenario < 95) {
            record.setAge(-1 * (1 + random.nextInt(20)));
            return record;
        }

        record.setCustomerId("");
        return record;
    }

    private void sendToIngestion(IncomingRecordDto record) {
        ResponseEntity<String> response =
                restTemplate.postForEntity(ingestionServiceUrl, record, String.class);

        System.out.println("External user sent to ingestion: "
                + record.getRecordId()
                + " | email=" + record.getEmail()
                + " | age=" + record.getAge()
                + " | status=" + response.getStatusCode());
    }
}