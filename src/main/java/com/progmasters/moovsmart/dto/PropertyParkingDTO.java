package com.progmasters.moovsmart.dto;

import com.progmasters.moovsmart.domain.PropertyParking;

public class PropertyParkingDTO {

    private String propertyParking;
    private String displayName;

    public PropertyParkingDTO() {
    }

    public PropertyParkingDTO(PropertyParking propertyParking) {
        this.propertyParking = propertyParking.toString();
        this.displayName = propertyParking.getDisplayName();
    }

    public String getPropertyParking() {
        return propertyParking;
    }

    public void setPropertyParking(String propertyParking) {
        this.propertyParking = propertyParking;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
