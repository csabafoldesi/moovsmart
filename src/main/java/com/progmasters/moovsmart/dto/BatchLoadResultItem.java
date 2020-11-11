package com.progmasters.moovsmart.dto;

import java.util.HashMap;
import java.util.Map;

public class BatchLoadResultItem {
    private PropertyForm propertyForm;
    private Map<String, String> errorList = new HashMap<>();

    public BatchLoadResultItem() {
    }

    public BatchLoadResultItem(PropertyForm propertyForm, Map<String, String> errorList) {
        this.propertyForm = propertyForm;
        this.errorList = errorList;
    }

    public PropertyForm getPropertyForm() {
        return propertyForm;
    }

    public void setPropertyForm(PropertyForm propertyForm) {
        this.propertyForm = propertyForm;
    }

    public Map<String, String> getErrorList() {
        return errorList;
    }

    public void setErrorList(Map<String, String> errorList) {
        this.errorList = errorList;
    }
}
