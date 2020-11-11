package com.progmasters.moovsmart.dto;

import com.progmasters.moovsmart.domain.Account;

public class AccountDetailsNewPasswordFormData {

    private String fullName;
    private String email;

    public AccountDetailsNewPasswordFormData() {
    }

    public AccountDetailsNewPasswordFormData(Account accountByNewPasswordKey) {
        this.fullName = accountByNewPasswordKey.getFullName();
        this.email = accountByNewPasswordKey.getEmail();
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
}
