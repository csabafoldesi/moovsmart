package com.progmasters.moovsmart.dto;

import java.util.ArrayList;
import java.util.List;

public class PropertyFormInitData {

    private List<PropertyTypeDTO> propertyTypes = new ArrayList<>();

    private List<PropertyConditionDTO> propertyConditions = new ArrayList<>();

    private List<PropertyHeatingDTO> propertyHeatings = new ArrayList<>();

    private List<PropertyParkingDTO> propertyParkings = new ArrayList<>();

    public PropertyFormInitData() {
    }

    public PropertyFormInitData(List<PropertyTypeDTO> propertyTypes, List<PropertyConditionDTO> propertyConditions,
                                List<PropertyHeatingDTO> propertyHeatings, List<PropertyParkingDTO> propertyParkings) {
        this.propertyTypes = propertyTypes;
        this.propertyConditions = propertyConditions;
        this.propertyHeatings = propertyHeatings;
        this.propertyParkings = propertyParkings;
    }

    public List<PropertyTypeDTO> getPropertyTypes() {
        return propertyTypes;
    }

    public void setPropertyTypes(List<PropertyTypeDTO> propertyTypes) {
        this.propertyTypes = propertyTypes;
    }

    public List<PropertyConditionDTO> getPropertyConditions() {
        return propertyConditions;
    }

    public void setPropertyConditions(List<PropertyConditionDTO> propertyConditions) {
        this.propertyConditions = propertyConditions;
    }

    public List<PropertyHeatingDTO> getPropertyHeatings() {
        return propertyHeatings;
    }

    public void setPropertyHeatings(List<PropertyHeatingDTO> propertyHeatings) {
        this.propertyHeatings = propertyHeatings;
    }

    public List<PropertyParkingDTO> getPropertyParkings() {
        return propertyParkings;
    }

    public void setPropertyParkings(List<PropertyParkingDTO> propertyParkings) {
        this.propertyParkings = propertyParkings;
    }
}
