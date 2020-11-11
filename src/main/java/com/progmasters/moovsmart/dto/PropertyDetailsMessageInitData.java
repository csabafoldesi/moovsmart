package com.progmasters.moovsmart.dto;

import com.progmasters.moovsmart.domain.Message;

public class PropertyDetailsMessageInitData {

    private Long propertyId;
    private String propertyName;

    public PropertyDetailsMessageInitData() {
    }

    public PropertyDetailsMessageInitData(Message message) {
        this.propertyId = message.getProperty().getId();
        this.propertyName = message.getProperty().getName();
    }

    public PropertyDetailsMessageInitData(Long propertyId, String propertyName) {
        this.propertyId = propertyId;
        this.propertyName = propertyName;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
}
