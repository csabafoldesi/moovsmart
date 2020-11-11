package com.progmasters.moovsmart.repository;

import com.progmasters.moovsmart.domain.*;
import com.progmasters.moovsmart.dto.PropertyForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PropertyRepositoryTest {

    private PropertyForm propertyForm;

    @Autowired
    private PropertyRepository propertyRepository;

    @BeforeEach
    public void setUp() {
        this.propertyForm = new PropertyForm();
        this.propertyForm.setName("Cosy apartment");
        this.propertyForm.setNumberOfRooms(3);
        this.propertyForm.setPrice(100000);
        this.propertyForm.setFloorArea(120);
        this.propertyForm.setLotSize(120);
        this.propertyForm.setBalconySize(6);
        this.propertyForm.setYearBuilt(2010);
        this.propertyForm.setDescription("This is a cosy apartment.");
        this.propertyForm.setPropertyType(PropertyType.APARTMENT.name());
        this.propertyForm.setPropertyCondition(PropertyCondition.NEWLY_BUILT.name());
        this.propertyForm.setPropertyHeating(PropertyHeating.COMBINATION_BOILER.name());
        this.propertyForm.setPropertyParking(PropertyParking.STREET.name());
        this.propertyForm.setCountry("Hungary");
        this.propertyForm.setZipCode("1056");
        this.propertyForm.setCity("Budapest");
        this.propertyForm.setStreet("Sas. u. 45. 1.em. 3/b");
    }

    @Test
    public void testFindAllByState() {
        List<Property> propertyList = propertyRepository.findAllByState(PropertyState.ACTIVE);
        Assertions.assertEquals(3, propertyList.size());
    }

    @Test
    public void testSave() {
        Property property = new Property(this.propertyForm);
        Property savedProperty = propertyRepository.save(property);
        Assertions.assertEquals(4, savedProperty.getId());
    }
}
