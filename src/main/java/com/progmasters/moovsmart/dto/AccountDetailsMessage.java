package com.progmasters.moovsmart.dto;

import com.progmasters.moovsmart.domain.Account;

public class AccountDetailsMessage {

    private Long id;
    private String email;
    private String fullName;
    private String phoneNumber;

    public AccountDetailsMessage() {
    }

    public AccountDetailsMessage(Account account) {
        this.id = account.getId();
        this.email = account.getEmail();
        this.fullName = account.getFullName();
        this.phoneNumber = account.getPhoneNumber();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
