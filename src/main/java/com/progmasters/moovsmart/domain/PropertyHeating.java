package com.progmasters.moovsmart.domain;

public enum PropertyHeating {

    COMBINATION_BOILER("combination boiler"),
    COMBINATION_BOILER_FLOOR_HEATING("combination boiler - floor heating"),
    NATURAL_GAS_BOILER("natural gas boiler"),
    NATURAL_GAS_BOILER_FLOOR_HEATING("natural gas boiler - floor heating"),
    FLOOR_HEATING("floor heating"),
    CENTRAL_HEATING("central heating"),
    OTHER("other");

    private final String displayName;

    PropertyHeating(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
