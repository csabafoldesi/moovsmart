package com.progmasters.moovsmart.dto;

import com.progmasters.moovsmart.domain.PropertyType;

public class PropertyTypeDTO {

    private String propertyType;
    private String displayName;

    public PropertyTypeDTO() {
    }

    public PropertyTypeDTO(PropertyType propertyType) {
        this.propertyType = propertyType.toString();
        this.displayName = propertyType.getDisplayName();
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
