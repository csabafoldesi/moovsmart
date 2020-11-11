package com.progmasters.moovsmart.domain;

public enum PropertyCondition {

    NEWLY_BUILT("newly built"),
    NOVEL("novel"),
    RENOVATED("renovated"),
    TO_BE_RENOVATED("to be renovated"),
    UNFINISHED("unfinished"),
    OTHER("other");

    private final String displayName;

    PropertyCondition(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
