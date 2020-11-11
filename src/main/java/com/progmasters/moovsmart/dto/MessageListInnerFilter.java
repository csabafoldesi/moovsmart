package com.progmasters.moovsmart.dto;

import com.progmasters.moovsmart.domain.MessageIncomingState;
import com.progmasters.moovsmart.domain.MessageSentState;

import java.time.LocalDateTime;


public class MessageListInnerFilter {

    private MessageIncomingState messageIncomingState;
    private MessageSentState messageSentState;
    private String authenticatedEmail;
    private LocalDateTime startDateTime = LocalDateTime.of(2020, 1, 1, 0, 0);
    private LocalDateTime endDateTime = LocalDateTime.now();
    private Long propertyId;

    public MessageIncomingState getMessageIncomingState() {
        return messageIncomingState;
    }

    public void setMessageIncomingState(MessageIncomingState messageIncomingState) {
        this.messageIncomingState = messageIncomingState;
    }

    public MessageSentState getMessageSentState() {
        return messageSentState;
    }

    public void setMessageSentState(MessageSentState messageSentState) {
        this.messageSentState = messageSentState;
    }

    public String getAuthenticatedEmail() {
        return authenticatedEmail;
    }

    public void setAuthenticatedEmail(String authenticatedEmail) {
        this.authenticatedEmail = authenticatedEmail;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }
}
