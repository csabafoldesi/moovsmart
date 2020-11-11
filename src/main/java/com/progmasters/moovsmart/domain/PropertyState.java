package com.progmasters.moovsmart.domain;

public enum PropertyState {
    PENDING("Pending"),
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    INACTIVE_VISITOR_ACCOUNT("Inactive visitor account");

    private final String displayName;

    PropertyState(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
