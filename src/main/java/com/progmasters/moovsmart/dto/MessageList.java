package com.progmasters.moovsmart.dto;

import java.util.List;

public class MessageList {
    private Integer totalItems;
    private List<MessageListItem> messageListItems;

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public List<MessageListItem> getMessageListItems() {
        return messageListItems;
    }

    public void setMessageListItems(List<MessageListItem> messageListItems) {
        this.messageListItems = messageListItems;
    }
}
