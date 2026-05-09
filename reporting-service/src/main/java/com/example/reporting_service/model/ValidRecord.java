package com.example.reporting_service.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "valid_records")
public class ValidRecord {

    @Id
    private Long id;

    private Integer age;
    private String city;
    private LocalDateTime createdAt;
    private String customerId;
    private String email;
    private String fullName;
    private String phoneNumber;
    private String recordId;
    private String source;

    public Long getId() {
        return id;
    }

    public Integer getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getRecordId() {
        return recordId;
    }

    public String getSource() {
        return source;
    }
}