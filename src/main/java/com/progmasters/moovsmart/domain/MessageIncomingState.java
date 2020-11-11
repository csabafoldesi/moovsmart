package com.progmasters.moovsmart.domain;

public enum MessageIncomingState {
    ACTIVE("Active"),
    INACTIVE("Inactive");

    private final String displayName;

    MessageIncomingState(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
