package com.progmasters.moovsmart.dto;

public class Location {
    private final float lng;
    private final float lat;

    public Location(float lng, float lat) {
        this.lng = lng;
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public float getLat() {
        return lat;
    }
}
