package com.progmasters.moovsmart.dto;

import com.progmasters.moovsmart.domain.PropertyCondition;

public class PropertyConditionDTO {

    private String propertyCondition;
    private String displayName;

    public PropertyConditionDTO() {
    }

    public PropertyConditionDTO(PropertyCondition propertyCondition) {
        this.propertyCondition = propertyCondition.toString();
        this.displayName = propertyCondition.getDisplayName();
    }

    public String getPropertyCondition() {
        return propertyCondition;
    }

    public void setPropertyCondition(String propertyCondition) {
        this.propertyCondition = propertyCondition;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
