package com.progmasters.moovsmart.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.progmasters.moovsmart.domain.Message;

import java.time.LocalDateTime;

public class MessageThreadListItem {

    private Long id;

    private String content;

    @JsonFormat(pattern = "YYYY. MMM dd - HH:mm:ss")
    private LocalDateTime timestampCreated;

    private AccountDetailsMessage fromAccountDetailsMessage;
    private AccountDetailsMessage toAccountDetailsMessage;

    public MessageThreadListItem() {
    }

    public MessageThreadListItem(Message message) {
        this.id = message.getId();
        this.content = message.getContent();
        this.timestampCreated = message.getTimestampCreated();
        this.fromAccountDetailsMessage = new AccountDetailsMessage(message.getFrom());
        this.toAccountDetailsMessage = new AccountDetailsMessage(message.getTo());
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
}
