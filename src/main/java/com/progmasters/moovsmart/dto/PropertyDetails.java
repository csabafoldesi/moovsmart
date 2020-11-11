package com.progmasters.moovsmart.dto;

import com.progmasters.moovsmart.domain.Property;

import java.util.ArrayList;
import java.util.List;

public class PropertyDetails {

    private Long id;
    private String name;
    private Integer numberOfRooms;
    private Integer price;
    private Integer floorArea;
    private Integer sqmPrice;
    private Integer lotSize;
    private Integer balconySize;
    private Integer yearBuilt;
    private String description;
    private PropertyTypeDTO propertyType;
    private PropertyConditionDTO propertyCondition;
    private PropertyHeatingDTO propertyHeating;
    private PropertyParkingDTO propertyParking;
    private AccountDetailsProperty accountDetailsProperty;
    private List<Image> imageList = new ArrayList<>();
    private String country;
    private String zipCode;
    private String city;
    private String street;
    private Location location;
    private Long visits;
    private Integer bookmarks;

    public PropertyDetails() {
    }

    public PropertyDetails(Property property) {
        this.id = property.getId();
        this.name = property.getName();
        this.numberOfRooms = property.getNumberOfRooms();
        this.price = property.getPrice();
        this.floorArea = property.getFloorArea();
        if (this.floorArea != null && this.floorArea != 0) {
            this.sqmPrice = property.getPrice() / this.floorArea;
        }
        this.lotSize = property.getLotSize();
        this.balconySize = property.getBalconySize();
        this.yearBuilt = property.getYearBuilt();
        this.description = property.getDescription();
        this.propertyType = new PropertyTypeDTO(property.getPropertyType());
        this.propertyCondition = new PropertyConditionDTO(property.getPropertyCondition());
        this.propertyHeating = new PropertyHeatingDTO(property.getPropertyHeating());
        this.propertyParking = new PropertyParkingDTO(property.getPropertyParking());
        if (property.getAccount() != null) {
            this.accountDetailsProperty = new AccountDetailsProperty(property.getAccount());
        }
        this.country = property.getCountry();
        this.zipCode = property.getZipCode();
        this.city = property.getCity();
        this.street = property.getStreet();
        if (property.getLng() != null) {
            this.location = new Location(property.getLng(), property.getLat());
        }
        this.visits = property.getVisits();
        this.bookmarks = property.getBookmarks().size();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AccountDetailsProperty getAccountDetailsProperty() {
        return accountDetailsProperty;
    }

    public void setAccountDetailsProperty(AccountDetailsProperty accountDetailsProperty) {
        this.accountDetailsProperty = accountDetailsProperty;
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

    public PropertyTypeDTO getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyTypeDTO propertyType) {
        this.propertyType = propertyType;
    }

    public PropertyConditionDTO getPropertyCondition() {
        return propertyCondition;
    }

    public void setPropertyCondition(PropertyConditionDTO propertyCondition) {
        this.propertyCondition = propertyCondition;
    }

    public PropertyHeatingDTO getPropertyHeating() {
        return propertyHeating;
    }

    public void setPropertyHeating(PropertyHeatingDTO propertyHeating) {
        this.propertyHeating = propertyHeating;
    }

    public PropertyParkingDTO getPropertyParking() {
        return propertyParking;
    }

    public void setPropertyParking(PropertyParkingDTO propertyParking) {
        this.propertyParking = propertyParking;
    }

    public Integer getSqmPrice() {
        return sqmPrice;
    }

    public void setSqmPrice(Integer sqmPrice) {
        this.sqmPrice = sqmPrice;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Long getVisits() {
        return visits;
    }

    public void setVisits(Long visits) {
        this.visits = visits;
    }

    public Integer getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(Integer bookmarks) {
        this.bookmarks = bookmarks;
    }
}
