package com.progmasters.moovsmart.dto;

import com.progmasters.moovsmart.domain.Property;
import com.progmasters.moovsmart.domain.Upload;

import java.util.List;

public class PropertyDetailsMessage {

    private Long id;
    private String name;
    private String country;
    private String zipCode;
    private String city;
    private String street;
    private String imageUrl;

    public PropertyDetailsMessage() {
    }

    public PropertyDetailsMessage(Property property) {
        this.id = property.getId();
        this.name = property.getName();
        this.country = property.getCountry();
        this.zipCode = property.getZipCode();
        this.city = property.getCity();
        this.street = property.getStreet();
        List<Upload> imageList = property.getImageList();
        if (!imageList.isEmpty()) {
            this.imageUrl = imageList.get(0).getFilePath();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
