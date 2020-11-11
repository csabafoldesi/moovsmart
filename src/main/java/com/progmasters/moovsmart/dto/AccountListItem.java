package com.progmasters.moovsmart.dto;

import com.progmasters.moovsmart.domain.Account;

public class AccountListItem {

    private Long id;
    private String email;
    private String fullName;

    public AccountListItem() {
    }

    public AccountListItem(Account account) {
        this.id = account.getId();
        this.email = account.getEmail();
        this.fullName = account.getFullName();
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
}
