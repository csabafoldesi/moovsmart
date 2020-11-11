package com.progmasters.moovsmart.dto;

import com.progmasters.moovsmart.domain.Property;
import com.progmasters.moovsmart.domain.Upload;

import java.util.List;

public class PropertyListItem {

    private Long id;
    private String name;
    private Integer numberOfRooms;
    private Integer price;
    private Integer floorArea;
    private Integer sqmPrice;
    private String imageUrl;
    private String smallImageUrl;
    private String country;
    private String zipCode;
    private String city;
    private String street;
    private Integer balconySize;
    private Integer lotSize;
    private String propertyCondition;
    private String propertyType;
    private Integer yearBuilt;
    private Long visits;

    public PropertyListItem() {
    }

    public PropertyListItem(Property property) {
        this.id = property.getId();
        this.name = property.getName();
        this.numberOfRooms = property.getNumberOfRooms();
        this.price = property.getPrice();
        this.floorArea = property.getFloorArea();
        if (this.floorArea != null && this.floorArea != 0) {
            this.sqmPrice = property.getPrice() / this.floorArea;
        }
        this.country = property.getCountry();
        this.zipCode = property.getZipCode();
        this.city = property.getCity();
        this.street = property.getStreet();
        this.balconySize = property.getBalconySize();
        this.lotSize = property.getLotSize();
        if (property.getPropertyCondition() != null) {
            this.propertyCondition = property.getPropertyCondition().getDisplayName();
        }
        if (property.getPropertyType() != null) {
            this.propertyType = property.getPropertyType().getDisplayName();
        }
        this.yearBuilt = property.getYearBuilt();
        this.visits = property.getVisits();
        List<Upload> imageList = property.getImageList();
        if (!imageList.isEmpty()) {
            this.imageUrl = imageList.get(0).getFilePath();
        }
    }

    public PropertyListItem(Property property, String smallImageUrl) {
        this(property);
        this.smallImageUrl = smallImageUrl;
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

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(Integer floorArea) {
        this.floorArea = floorArea;
    }

    public Integer getSqmPrice() {
        return sqmPrice;
    }

    public void setSqmPrice(Integer sqmPrice) {
        this.sqmPrice = sqmPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSmallImageUrl() {
        return smallImageUrl;
    }

    public void setSmallImageUrl(String smallImageUrl) {
        this.smallImageUrl = smallImageUrl;
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

    public Integer getBalconySize() {
        return balconySize;
    }

    public void setBalconySize(Integer balconySize) {
        this.balconySize = balconySize;
    }

    public Integer getLotSize() {
        return lotSize;
    }

    public void setLotSize(Integer lotSize) {
        this.lotSize = lotSize;
    }

    public String getPropertyCondition() {
        return propertyCondition;
    }

    public void setPropertyCondition(String propertyCondition) {
        this.propertyCondition = propertyCondition;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public Integer getYearBuilt() {
        return yearBuilt;
    }

    public void setYearBuilt(Integer yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public Long getVisits() {
        return visits;
    }

    public void setVisits(Long visits) {
        this.visits = visits;
    }
}
