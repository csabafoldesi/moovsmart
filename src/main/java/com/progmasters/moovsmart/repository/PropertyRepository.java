package com.progmasters.moovsmart.repository;

import com.progmasters.moovsmart.domain.Property;
import com.progmasters.moovsmart.domain.PropertyState;
import com.progmasters.moovsmart.dto.PropertyListFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long>, JpaSpecificationExecutor<Property> {

    @Query("SELECT p FROM Property p WHERE p.state = :state")
    List<Property> findAllByState(@Param("state") PropertyState state);

    @Query("SELECT p FROM Property p WHERE p.state = :state")
    Page<Property> findAllByState(@Param("state") PropertyState state, Pageable paging);

    @Query("SELECT count(p.id) FROM Property p WHERE p.state = :state")
    Long countAllById(@Param("state") PropertyState state);

    @Query(
            "SELECT p FROM Property p " +
                    "WHERE p.state = :state AND " +
                    "p.price BETWEEN :#{#propertyListFilter.minPrice} AND :#{#propertyListFilter.maxPrice} AND " +
                    "p.numberOfRooms BETWEEN :#{#propertyListFilter.minNumberOfRooms} AND :#{#propertyListFilter.maxNumberOfRooms} AND " +
                    "p.floorArea BETWEEN :#{#propertyListFilter.minFloorArea} AND :#{#propertyListFilter.maxFloorArea} AND " +
                    "(p.price / p.floorArea) BETWEEN :#{#propertyListFilter.minSqmPrice} AND :#{#propertyListFilter.maxSqmPrice} "
    )
    Page<Property> findAllWithFilter(@Param("state") PropertyState state, Pageable paging, PropertyListFilter propertyListFilter);

    @Query(
            "SELECT count(p.id) FROM Property p " +
                    "WHERE p.state = :state AND " +
                    "p.price BETWEEN :#{#propertyListFilter.minPrice} AND :#{#propertyListFilter.maxPrice} AND " +
                    "p.numberOfRooms BETWEEN :#{#propertyListFilter.minNumberOfRooms} AND :#{#propertyListFilter.maxNumberOfRooms} AND " +
                    "p.floorArea BETWEEN :#{#propertyListFilter.minFloorArea} AND :#{#propertyListFilter.maxFloorArea} AND " +
                    "(p.price / p.floorArea) BETWEEN :#{#propertyListFilter.minSqmPrice} AND :#{#propertyListFilter.maxSqmPrice} "
    )
    Integer countAllByIdWithFilter(@Param("state") PropertyState state, PropertyListFilter propertyListFilter);

    @Query(
            "SELECT p FROM Property p " +
                    "JOIN Upload u ON p.id = u.property.id " +
                    "WHERE p.state = :state AND " +
                    "p.price BETWEEN :#{#propertyListFilter.minPrice} AND :#{#propertyListFilter.maxPrice} AND " +
                    "p.numberOfRooms BETWEEN :#{#propertyListFilter.minNumberOfRooms} AND :#{#propertyListFilter.maxNumberOfRooms} AND " +
                    "p.floorArea BETWEEN :#{#propertyListFilter.minFloorArea} AND :#{#propertyListFilter.maxFloorArea} AND " +
                    "(p.price / p.floorArea) BETWEEN :#{#propertyListFilter.minSqmPrice} AND :#{#propertyListFilter.maxSqmPrice} " +
                    "GROUP BY u.property.id "
    )
    Page<Property> findAllWithFilterAndWithPicture(@Param("state") PropertyState state, Pageable paging, PropertyListFilter propertyListFilter);

    @Query(
            "SELECT p.id FROM Property p " +
                    "JOIN Upload u ON p.id = u.property.id " +
                    "WHERE p.state = :state AND " +
                    "p.price BETWEEN :#{#propertyListFilter.minPrice} AND :#{#propertyListFilter.maxPrice} AND " +
                    "p.numberOfRooms BETWEEN :#{#propertyListFilter.minNumberOfRooms} AND :#{#propertyListFilter.maxNumberOfRooms} AND " +
                    "p.floorArea BETWEEN :#{#propertyListFilter.minFloorArea} AND :#{#propertyListFilter.maxFloorArea} AND " +
                    "(p.price / p.floorArea) BETWEEN :#{#propertyListFilter.minSqmPrice} AND :#{#propertyListFilter.maxSqmPrice} " +
                    "GROUP BY u.property.id "
    )
    List<Long> countAllByIdWithFilterAndWithPicture(@Param("state") PropertyState state, PropertyListFilter propertyListFilter);

    Page<Property> findAll(Pageable pageable);

    @Query("SELECT DISTINCT p.city FROM Property p where p.city like %:keyword%")
    List<String> searchCity(@Param("keyword") String keyword);

    @Query("SELECT p FROM Property p WHERE p.state = :state AND p.id = :id")
    Optional<Property> findAllByStateAndId(@Param("state") PropertyState state, @Param("id") Long id);

}
