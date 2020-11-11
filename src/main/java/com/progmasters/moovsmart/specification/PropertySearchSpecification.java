package com.progmasters.moovsmart.specification;

import com.progmasters.moovsmart.domain.Property;
import com.progmasters.moovsmart.domain.PropertyState;
import com.progmasters.moovsmart.dto.PropertyListFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;

public class PropertySearchSpecification {


    public static PropertyListFilter propertyListFilter;

    public static Specification<Property> isActive() {
        return (Specification<Property>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("state"), PropertyState.ACTIVE);
    }

    public static Specification<Property> setPriceRange() {
        return (Specification<Property>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.between(
                root.get("price"),
                propertyListFilter.getMinPrice(),
                propertyListFilter.getMaxPrice());
    }

    public static Specification<Property> setNumberOfRoomsRange() {
        return (Specification<Property>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.between(
                root.get("numberOfRooms"),
                propertyListFilter.getMinNumberOfRooms(),
                propertyListFilter.getMaxNumberOfRooms());
    }

    public static Specification<Property> setFloorAreaRange() {
        return (Specification<Property>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.between(
                root.get("floorArea"),
                propertyListFilter.getMinFloorArea(),
                propertyListFilter.getMaxFloorArea());
    }

    public static Specification<Property> setSqmPriceRange() {
        return (Specification<Property>) (root, criteriaQuery, criteriaBuilder) -> {
            Expression<Integer> price = root.get("price");
            Expression<Integer> floorArea = root.get("floorArea");
            Expression<Integer> sqmPrice = criteriaBuilder.quot(price, floorArea).as(Integer.class);
            return criteriaBuilder.between(
                    sqmPrice,
                    propertyListFilter.getMinSqmPrice(),
                    propertyListFilter.getMaxSqmPrice());
        };
    }

    public static Specification<Property> onlyWithPicture() {
        return (Specification<Property>) (root, criteriaQuery, criteriaBuilder) -> {
            if (propertyListFilter.getWithPicture()) {
                root.join("imageList");
                criteriaQuery.distinct(true);
            }
            return null;
        };
    }

    public static Specification<Property> citySearch() {
        return (Specification<Property>) (root, criteriaQuery, criteriaBuilder) -> {
            if (!propertyListFilter.getCityControl().equals("")) {
                return criteriaBuilder.equal(
                        root.get("city"),
                        propertyListFilter.getCityControl());
            }
            return null;
        };

    }
}
