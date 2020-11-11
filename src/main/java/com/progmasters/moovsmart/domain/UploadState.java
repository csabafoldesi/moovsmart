package com.progmasters.moovsmart.domain;

public enum UploadState {
    ACTIVE("Active"),
    DELETED("Deleted");

    private final String displayName;

    UploadState(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
