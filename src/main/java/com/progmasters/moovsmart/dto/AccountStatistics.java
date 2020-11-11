package com.progmasters.moovsmart.dto;

public class AccountStatistics {

    private Long numberOfOwners;
    private Long numberOfVisitors;
    private Long numberOfInactive;
    private Long numberOfUnconfirmed;

    public Long getNumberOfOwners() {
        return numberOfOwners;
    }

    public void setNumberOfOwners(Long numberOfOwners) {
        this.numberOfOwners = numberOfOwners;
    }

    public Long getNumberOfVisitors() {
        return numberOfVisitors;
    }

    public void setNumberOfVisitors(Long numberOfVisitors) {
        this.numberOfVisitors = numberOfVisitors;
    }

    public Long getNumberOfInactive() {
        return numberOfInactive;
    }

    public void setNumberOfInactive(Long numberOfInactive) {
        this.numberOfInactive = numberOfInactive;
    }

    public Long getNumberOfUnconfirmed() {
        return numberOfUnconfirmed;
    }

    public void setNumberOfUnconfirmed(Long numberOfUnconfirmed) {
        this.numberOfUnconfirmed = numberOfUnconfirmed;
    }
}
