package com.progmasters.moovsmart.domain;

public enum MessageSentState {
    ACTIVE("Active"),
    INACTIVE("Inactive");

    private final String displayName;

    MessageSentState(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
