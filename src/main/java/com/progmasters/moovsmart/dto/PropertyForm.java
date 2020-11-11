package com.progmasters.moovsmart.dto;

import java.util.ArrayList;
import java.util.List;

public class PropertyForm {

    private String name;
    private Integer numberOfRooms;
    private Integer price;
    private Integer floorArea;
    private Integer lotSize;
    private Integer balconySize;
    private Integer yearBuilt;
    private String description;
    private String propertyType;
    private String propertyCondition;
    private String propertyHeating;
    private String propertyParking;
    private List<Long> imageList = new ArrayList<>();
    private String country;
    private String zipCode;
    private String city;
    private String street;

    public PropertyForm() {
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

    public Integer getLotSize() {
        return lotSize;
    }

    public void setLotSize(Integer lotSize) {
        this.lotSize = lotSize;
    }

    public Integer getBalconySize() {
        return balconySize;
    }

    public void setBalconySize(Integer balconySize) {
        this.balconySize = balconySize;
    }

    public Integer getYearBuilt() {
        return yearBuilt;
    }

    public void setYearBuilt(Integer yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getPropertyCondition() {
        return propertyCondition;
    }

    public void setPropertyCondition(String propertyCondition) {
        this.propertyCondition = propertyCondition;
    }

    public String getPropertyHeating() {
        return propertyHeating;
    }

    public void setPropertyHeating(String propertyHeating) {
        this.propertyHeating = propertyHeating;
    }

    public String getPropertyParking() {
        return propertyParking;
    }

    public void setPropertyParking(String propertyParking) {
        this.propertyParking = propertyParking;
    }

    public List<Long> getImageList() {
        return imageList;
    }

    public void setImageList(List<Long> imageList) {
        this.imageList = imageList;
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
}
