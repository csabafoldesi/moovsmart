package com.progmasters.moovsmart.dto;

import java.util.List;

public class PropertyList {
    private Long totalItems;
    private List<PropertyListItem> propertyListItems;

    public Long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Long totalItems) {
        this.totalItems = totalItems;
    }

    public List<PropertyListItem> getPropertyListItems() {
        return propertyListItems;
    }

    public void setPropertyListItems(List<PropertyListItem> propertyListItems) {
        this.propertyListItems = propertyListItems;
    }
}
