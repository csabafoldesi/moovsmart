package com.progmasters.moovsmart.dto;

import com.progmasters.moovsmart.domain.PropertyHeating;

public class PropertyHeatingDTO {

    private String propertyHeating;
    private String displayName;

    public PropertyHeatingDTO() {
    }

    public PropertyHeatingDTO(PropertyHeating propertyHeating) {
        this.propertyHeating = propertyHeating.toString();
        this.displayName = propertyHeating.getDisplayName();
    }

    public String getPropertyHeating() {
        return propertyHeating;
    }

    public void setPropertyHeating(String propertyHeating) {
        this.propertyHeating = propertyHeating;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
