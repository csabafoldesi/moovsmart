package com.progmasters.moovsmart.domain;

public enum PropertyType {

    APARTMENT("apartment"),
    HOUSE("house"),
    GARAGE("garage"),
    COTTAGE("cottage"),
    OFFICE("office"),
    RETAIL_UNIT("retail unit"),
    OTHER("other");

    private final String displayName;

    PropertyType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
