package com.progmasters.moovsmart.domain;

import com.progmasters.moovsmart.dto.PropertyForm;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "property")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "number_of_rooms")
    private Integer numberOfRooms;

    @Column(name = "price")
    private Integer price;

    @Column(name = "floor_area")
    private Integer floorArea;

    @Column(name = "lot_size")
    private Integer lotSize;

    @Column(name = "balcony_size")
    private Integer balconySize;

    @Column(name = "year_built")
    private Integer yearBuilt;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "property_type")
    private PropertyType propertyType;

    @Enumerated(EnumType.STRING)
    @Column(name = "property_condition")
    private PropertyCondition propertyCondition;

    @Enumerated(EnumType.STRING)
    @Column(name = "property_heating")
    private PropertyHeating propertyHeating;

    @Enumerated(EnumType.STRING)
    @Column(name = "property_parking")
    private PropertyParking propertyParking;

    @OneToMany(mappedBy = "property")
    @Where(clause = "state = 'ACTIVE'")
    private List<Upload> imageList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private PropertyState state = PropertyState.PENDING;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "country")
    private String country;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "lat")
    private Float lat;

    @Column(name = "lng")
    private Float lng;

    @OneToMany(mappedBy = "property")
    private Set<Bookmark> bookmarks;

    @Column(name = "visits")
    private Long visits;

    public Property() {
    }

    public Property(PropertyForm propertyForm) {
        this.name = propertyForm.getName().trim();
        this.numberOfRooms = propertyForm.getNumberOfRooms();
        this.price = propertyForm.getPrice();
        this.floorArea = propertyForm.getFloorArea();
        this.lotSize = propertyForm.getLotSize();
        this.balconySize = propertyForm.getBalconySize();
        this.yearBuilt = propertyForm.getYearBuilt();
        if (propertyForm.getDescription() != null) {
            this.description = propertyForm.getDescription().trim();
        }
        if (propertyForm.getCountry() != null) {
            this.country = propertyForm.getCountry().trim();
        }
        if (propertyForm.getZipCode() != null) {
            this.zipCode = propertyForm.getZipCode().trim();
        }
        if (propertyForm.getCity() != null) {
            this.city = propertyForm.getCity().trim();
        }
        if (propertyForm.getStreet() != null) {
            this.street = propertyForm.getStreet().trim();
        }
        setPropertyDetails(propertyForm);
        this.visits = 0L;
    }

    private void setPropertyDetails(PropertyForm propertyForm) {
        if (propertyForm.getPropertyType() != null) {
            this.propertyType = PropertyType.valueOf(propertyForm
                    .getPropertyType());
        }
        if (propertyForm.getPropertyCondition() != null) {
            this.propertyCondition = PropertyCondition.valueOf(propertyForm
                    .getPropertyCondition());
        }
        if (propertyForm.getPropertyHeating() != null) {
            this.propertyHeating = PropertyHeating.valueOf(propertyForm
                    .getPropertyHeating());
        }
        if (propertyForm.getPropertyParking() != null) {
            this.propertyParking = PropertyParking.valueOf(propertyForm
                    .getPropertyParking());
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

    public List<Upload> getImageList() {
        return imageList;
    }

    public void setImageList(List<Upload> imageList) {
        this.imageList = imageList;
    }

    public PropertyState getState() {
        return state;
    }

    public void setState(PropertyState state) {
        this.state = state;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public PropertyCondition getPropertyCondition() {
        return propertyCondition;
    }

    public void setPropertyCondition(PropertyCondition propertyCondition) {
        this.propertyCondition = propertyCondition;
    }

    public Integer getYearBuilt() {
        return yearBuilt;
    }

    public void setYearBuilt(Integer yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public PropertyParking getPropertyParking() {
        return propertyParking;
    }

    public void setPropertyParking(PropertyParking propertyParking) {
        this.propertyParking = propertyParking;
    }

    public Integer getBalconySize() {
        return balconySize;
    }

    public void setBalconySize(Integer propertyBalcony) {
        this.balconySize = propertyBalcony;
    }

    public PropertyHeating getPropertyHeating() {
        return propertyHeating;
    }

    public void setPropertyHeating(PropertyHeating propertyHeating) {
        this.propertyHeating = propertyHeating;
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

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    public Set<Bookmark> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(Set<Bookmark> bookmarks) {
        this.bookmarks = bookmarks;
    }

    public Long getVisits() {
        return visits;
    }

    public void setVisits(Long visits) {
        this.visits = visits;
    }
}
