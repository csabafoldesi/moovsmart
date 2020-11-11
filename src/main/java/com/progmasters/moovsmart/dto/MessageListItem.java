package com.progmasters.moovsmart.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.progmasters.moovsmart.domain.Message;

import java.time.LocalDateTime;

public class MessageListItem {

    private Long id;

    private String content;

    @JsonFormat(pattern = "YYYY. MMM dd - HH:mm:ss")
    private LocalDateTime timestampCreated;

    private Boolean unRead;

    private AccountDetailsMessage fromAccountDetailsMessage;
    private AccountDetailsMessage toAccountDetailsMessage;

    private PropertyDetailsMessage propertyDetailsMessage;

    public MessageListItem() {
    }

    public MessageListItem(Message message) {
        this.id = message.getId();
        this.content = message.getContent();
        this.timestampCreated = message.getTimestampCreated();
        this.unRead = message.getUnRead();
        this.fromAccountDetailsMessage = new AccountDetailsMessage(message.getFrom());
        this.toAccountDetailsMessage = new AccountDetailsMessage(message.getTo());
        this.propertyDetailsMessage = new PropertyDetailsMessage(message.getProperty());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestampCreated() {
        return timestampCreated;
    }

    public void setTimestampCreated(LocalDateTime timestampCreated) {
        this.timestampCreated = timestampCreated;
    }

    public AccountDetailsMessage getFromAccountDetailsMessage() {
        return fromAccountDetailsMessage;
    }

    public void setFromAccountDetailsMessage(AccountDetailsMessage fromAccountDetailsMessage) {
        this.fromAccountDetailsMessage = fromAccountDetailsMessage;
    }

    public AccountDetailsMessage getToAccountDetailsMessage() {
        return toAccountDetailsMessage;
    }

    public void setToAccountDetailsMessage(AccountDetailsMessage toAccountDetailsMessage) {
        this.toAccountDetailsMessage = toAccountDetailsMessage;
    }

    public Boolean getUnRead() {
        return unRead;
    }

    public void setUnRead(Boolean unRead) {
        this.unRead = unRead;
    }

    public PropertyDetailsMessage getPropertyDetailsMessage() {
        return propertyDetailsMessage;
    }

    public void setPropertyDetailsMessage(PropertyDetailsMessage propertyDetailsMessage) {
        this.propertyDetailsMessage = propertyDetailsMessage;
    }
}
