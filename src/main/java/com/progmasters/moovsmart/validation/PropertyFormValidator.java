package com.progmasters.moovsmart.validation;

import com.progmasters.moovsmart.dto.PropertyForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PropertyFormValidator implements Validator {
    private static final String TEST_COUNTRY = "^[\\p{L} ]*$";
    private static final String TEST_CITY = "^[\\p{L} ]*$";
    private static final String TEST_ZIP = "^[\\p{L}0-9]*$";
    private static final String TEST_STREET = "^[\\p{L} 0-9.,\\/]*$";
    private static final String TEST_TITLE = "^[\\p{L} 0-9.,\\/\\(\\)\\+\\!\\?\"']*$";

    @Override
    public boolean supports(Class<?> aClass) {
        return PropertyForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PropertyForm property = (PropertyForm) o;
        String name = property.getName();
        Integer numberOfRooms = property.getNumberOfRooms();
        Integer price = property.getPrice();
        Integer floorArea = property.getFloorArea();
        Integer lotSize = property.getLotSize();
        Integer balconySize = property.getBalconySize();
        Integer yearBuilt = property.getYearBuilt();
        String description = property.getDescription();
        String propertyType = property.getPropertyType();
        String propertyCondition = property.getPropertyCondition();
        String propertyHeating = property.getPropertyHeating();
        String propertyParking = property.getPropertyParking();

        String country = property.getCountry();
        String zipCode = property.getZipCode();
        String city = property.getCity();
        String street = property.getStreet();

        if (name == null || name.equals("")) {
            errors.rejectValue("name", "property.name.empty");
        } else if (name.trim().length() > 200) {
            errors.rejectValue("name", "property.name.invalid-length");
        } else if (!regexTest(name, TEST_TITLE)) {
            errors.rejectValue("name", "property.name.invalid-chars");
        }

        if (numberOfRooms == null) {
            errors.rejectValue("numberOfRooms", "property.numberOfRooms.empty");
        } else if (numberOfRooms < 1 || numberOfRooms > 12) {
            errors.rejectValue("numberOfRooms", "property.numberOfRooms.invalid");
        }

        if (price == null) {
            errors.rejectValue("price", "property.price.empty");
        } else if (price < 1 || price > 10_000_000) {
            errors.rejectValue("price", "property.price.invalid");
        }

        if (floorArea == null) {
            errors.rejectValue("floorArea", "property.floorArea.empty");
        } else if (floorArea < 2 || floorArea > 1000) {
            errors.rejectValue("floorArea", "property.floorArea.invalid");
        }

        if (lotSize == null) {
            errors.rejectValue("lotSize", "property.lotSize.empty");
        } else if (lotSize < 2 || lotSize > 10000) {
            errors.rejectValue("lotSize", "property.lotSize.invalid");
        } else if (floorArea != null && lotSize < floorArea) {
            errors.rejectValue("lotSize", "property.lotSize.smaller.invalid");
        }

        if (balconySize != null && (balconySize < 0 || balconySize > 100)) {
            errors.rejectValue("balconySize", "property.balconySize.invalid");
        }

        if (yearBuilt == null) {
            errors.rejectValue("yearBuilt", "property.yearBuilt.empty");
        } else if (yearBuilt < 1200 || yearBuilt > LocalDateTime.now().getYear()) {
            errors.rejectValue("yearBuilt", "property.yearBuilt.invalid");
        }

        if (description == null || description.equals("")) {
            errors.rejectValue("description", "property.description.empty");
        } else if (description.trim().length() > 2000) {
            errors.rejectValue("description", "property.description.invalid-length");
        } else if (!regexTest(description, TEST_TITLE)) {
            errors.rejectValue("description", "property.description.invalid-chars");
        }

        checkPropertyType(errors, propertyType);
        checkPropertyCondition(errors, propertyCondition);
        checkPropertyHeating(errors, propertyHeating);
        checkPropertyParking(errors, propertyParking);

        if (country == null || country.equals("")) {
            errors.rejectValue("country", "property.country.empty");
        } else if (country.trim().length() < 4 || country.trim().length() > 60) {
            errors.rejectValue("country", "property.country.invalid-length");
        } else if (!regexTest(country, TEST_COUNTRY)) {
            errors.rejectValue("country", "property.country.invalid-chars");
        }

        if (zipCode == null || zipCode.equals("")) {
            errors.rejectValue("zipCode", "property.zipCode.empty");
        } else if (zipCode.trim().length() > 20) {
            errors.rejectValue("zipCode", "property.zipCode.invalid-length");
        } else if (!regexTest(zipCode, TEST_ZIP)) {
            errors.rejectValue("zipCode", "property.zipCode.invalid-chars");
        }

        if (city == null || city.equals("")) {
            errors.rejectValue("city", "property.city.empty");
        } else if (city.trim().length() < 2 || city.trim().length() > 180) {
            errors.rejectValue("city", "property.city.invalid-length");
        } else if (!regexTest(city, TEST_CITY)) {
            errors.rejectValue("city", "property.city.invalid-chars");
        }

        if (street == null || street.equals("")) {
            errors.rejectValue("street", "property.street.empty");
        } else if (street.length() < 8 || street.length() > 150) {
            errors.rejectValue("street", "property.street.invalid-length");
        } else if (!regexTest(street, TEST_STREET)) {
            errors.rejectValue("street", "property.street.invalid-chars");
        }
    }

    private void checkPropertyType(Errors errors, String propertyType) {
        if (propertyType == null || propertyType.isEmpty()) {
            errors.rejectValue("propertyType", "property.propertyTypeDTO.empty");
        }
    }

    private void checkPropertyCondition(Errors errors, String propertyCondition) {
        if (propertyCondition == null || propertyCondition.isEmpty()) {
            errors.rejectValue("propertyCondition", "property.propertyConditionDTO.empty");
        }
    }

    private void checkPropertyHeating(Errors errors, String propertyHeating) {
        if (propertyHeating == null || propertyHeating.isEmpty()) {
            errors.rejectValue("propertyHeating", "property.propertyHeatingDTO.empty");
        }
    }

    private void checkPropertyParking(Errors errors, String propertyParking) {
        if (propertyParking == null || propertyParking.isEmpty()) {
            errors.rejectValue("propertyParking", "property.propertyParkingDTO.empty");
        }
    }

    private boolean regexTest(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text.trim());
        return matcher.matches();
    }
}
