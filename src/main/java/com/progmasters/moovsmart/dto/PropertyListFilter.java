package com.progmasters.moovsmart.dto;

public class PropertyListFilter {
    private Integer minPrice = 0;
    private Integer maxPrice = Integer.MAX_VALUE;
    private Integer minNumberOfRooms = 0;
    private Integer maxNumberOfRooms = Integer.MAX_VALUE;
    private Integer minFloorArea = 0;
    private Integer maxFloorArea = Integer.MAX_VALUE;
    private Integer minSqmPrice = 0;
    private Integer maxSqmPrice = Integer.MAX_VALUE;
    private Boolean withPicture = false;
    private String cityControl = "";

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getMinNumberOfRooms() {
        return minNumberOfRooms;
    }

    public void setMinNumberOfRooms(Integer minNumberOfRooms) {
        this.minNumberOfRooms = minNumberOfRooms;
    }

    public Integer getMaxNumberOfRooms() {
        return maxNumberOfRooms;
    }

    public void setMaxNumberOfRooms(Integer maxNumberOfRooms) {
        this.maxNumberOfRooms = maxNumberOfRooms;
    }

    public Integer getMinFloorArea() {
        return minFloorArea;
    }

    public void setMinFloorArea(Integer minFloorArea) {
        this.minFloorArea = minFloorArea;
    }

    public Integer getMaxFloorArea() {
        return maxFloorArea;
    }

    public void setMaxFloorArea(Integer maxFloorArea) {
        this.maxFloorArea = maxFloorArea;
    }

    public Integer getMinSqmPrice() {
        return minSqmPrice;
    }

    public void setMinSqmPrice(Integer minSqmPrice) {
        this.minSqmPrice = minSqmPrice;
    }

    public Integer getMaxSqmPrice() {
        return maxSqmPrice;
    }

    public void setMaxSqmPrice(Integer maxSqmPrice) {
        this.maxSqmPrice = maxSqmPrice;
    }

    public Boolean getWithPicture() {
        return withPicture;
    }

    public void setWithPicture(Boolean withPicture) {
        this.withPicture = withPicture;
    }

    public String getCityControl() {
        return cityControl;
    }

    public void setCityControl(String cityControl) {
        this.cityControl = cityControl;
    }
}
