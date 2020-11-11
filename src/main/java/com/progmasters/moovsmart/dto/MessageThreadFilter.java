package com.progmasters.moovsmart.dto;

import com.progmasters.moovsmart.domain.MessageIncomingState;
import com.progmasters.moovsmart.domain.MessageSentState;

public class MessageThreadFilter {

    private MessageIncomingState messageIncomingState;
    private MessageSentState messageSentState;
    private String authenticatedEmail;
    private String emailOtherPerson;
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

    public String getEmailOtherPerson() {
        return emailOtherPerson;
    }

    public void setEmailOtherPerson(String emailOtherPerson) {
        this.emailOtherPerson = emailOtherPerson;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }
}
