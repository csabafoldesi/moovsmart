package com.progmasters.moovsmart.dto;

import com.progmasters.moovsmart.domain.Account;

public class AccountProfileDetails {

    private String fullName;
    private String email;
    private String phoneNumber;
    private RoleDTO role;

    public AccountProfileDetails() {
    }

    public AccountProfileDetails(Account account) {
        this.fullName = account.getFullName();
        this.email = account.getEmail();
        this.phoneNumber = account.getPhoneNumber();
        this.role = new RoleDTO(account.getRole());
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }
}
