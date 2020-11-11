package com.progmasters.moovsmart.domain;

public enum Role {
    ROLE_VISITOR("visitor"),
    ROLE_OWNER("owner"),
    ROLE_ADMIN("admin");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
