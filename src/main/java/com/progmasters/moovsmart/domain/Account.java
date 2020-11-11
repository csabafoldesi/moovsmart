package com.progmasters.moovsmart.domain;

import com.progmasters.moovsmart.dto.AccountCreateCommand;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @Column(name = "activation_key")
    private String activationKey;

    @Column(name = "new_password_key")
    private String newPasswordKey;

    @OneToMany(mappedBy = "to")
    private List<Message> incomingMessages = new ArrayList<>();

    @OneToMany(mappedBy = "from")
    private List<Message> outgoingMessages = new ArrayList<>();

    @OneToMany(mappedBy = "account")
    private List<Property> propertyList = new ArrayList<>();

    @OneToMany(mappedBy = "account")
    private Set<Bookmark> bookmarks;

    public Account() {
    }

    public Account(AccountCreateCommand accountCreateCommand) {
        this.fullName = accountCreateCommand.getFullName();
        this.email = accountCreateCommand.getEmail();
        this.role = Role.valueOf(accountCreateCommand.getRole());
        this.accountStatus = AccountStatus.UNCONFIRMED;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public List<Message> getIncomingMessages() {
        return incomingMessages;
    }

    public void setIncomingMessages(List<Message> incomingMessages) {
        this.incomingMessages = incomingMessages;
    }

    public List<Message> getOutgoingMessages() {
        return outgoingMessages;
    }

    public void setOutgoingMessages(List<Message> outgoingMessages) {
        this.outgoingMessages = outgoingMessages;
    }

    public List<Property> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<Property> propertyList) {
        this.propertyList = propertyList;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNewPasswordKey() {
        return newPasswordKey;
    }

    public void setNewPasswordKey(String newPasswordKey) {
        this.newPasswordKey = newPasswordKey;
    }

    public Set<Bookmark> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(Set<Bookmark> bookmarks) {
        this.bookmarks = bookmarks;
    }
}
