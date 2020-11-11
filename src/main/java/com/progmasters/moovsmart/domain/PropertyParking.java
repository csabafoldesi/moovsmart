package com.progmasters.moovsmart.domain;

public enum PropertyParking {

    STREET("street"),
    UNDERGROUND_PARKING_SPACE("underground parking space"),
    GARDEN_PARKING_LOT("garden parking lot"),
    GARAGE("garage"),
    OTHER("other");

    private final String displayName;

    PropertyParking(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
