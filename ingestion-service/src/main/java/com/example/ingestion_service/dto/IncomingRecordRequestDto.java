package com.example.ingestion_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class IncomingRecordRequestDto {

    @NotBlank(message = "Record id cannot be blank")
    private String recordId;

    @NotBlank(message = "Customer id cannot be blank")
    private String customerId;

    @NotBlank(message = "Full name cannot be blank")
    private String fullName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email format is invalid")
    private String email;

    @NotBlank(message = "Phone number cannot be blank")
    private String phoneNumber;

    @NotNull(message = "Age cannot be null")
    private Integer age;

    @NotBlank(message = "City cannot be blank")
    private String city;

    @NotBlank(message = "Source cannot be blank")
    private String source;

    public IncomingRecordRequestDto() {
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