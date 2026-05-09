package com.example.external_user_collector_service.dto;

public class IncomingRecordDto {

    private String recordId;
    private String customerId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private Integer age;
    private String city;
    private String source;

    public IncomingRecordDto() {
    }

    public IncomingRecordDto(String recordId, String customerId, String fullName,
                             String email, String phoneNumber, Integer age,
                             String city, String source) {
        this.recordId = recordId;
        this.customerId = customerId;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.city = city;
        this.source = source;
    }

    public String getRecordId() {
        return recordId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Integer getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public String getSource() {
        return source;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setSource(String source) {
        this.source = source;
    }
}