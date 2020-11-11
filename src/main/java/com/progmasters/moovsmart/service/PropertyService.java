package com.progmasters.moovsmart.service;

import com.progmasters.moovsmart.domain.*;
import com.progmasters.moovsmart.dto.*;
import com.progmasters.moovsmart.repository.PropertyRepository;
import com.progmasters.moovsmart.specification.PropertySearchSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PropertyService {


    private static final String ADMIN_ACTION_ACTIVATE = " has been activated by our admin: ";
    private static final String ADMIN_ACTION_INACTIVATE = " has been inactivated by our admin: ";
    private static final String ADMIN_ACTION_UPDATE = " has been updated by our admin: ";
    private static final Logger logger = LoggerFactory.getLogger(PropertyService.class);
    private final PropertyRepository propertyRepository;
    private final AccountService accountService;
    private final FileUploader fileUploader;
    private final MapService mapService;
    private final MessageService messageService;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository,
                           FileUploader fileUploader,
                           AccountService accountService,
                           MapService mapService,
                           MessageService messageService) {
        this.propertyRepository = propertyRepository;
        this.fileUploader = fileUploader;
        this.accountService = accountService;
        this.mapService = mapService;
        this.messageService = messageService;
    }

    public List<PropertyListItem> getActiveProperties() {
        List<Property> properties = propertyRepository.findAllByState(PropertyState.ACTIVE);
        return properties.stream().map(PropertyListItem::new).collect(Collectors.toList());
    }

    public PropertyList getActiveProperties(Integer page, Integer pageSize, String sortBy) {
        Pageable paging;
        if (sortBy.contains(".desc")) {
            int toDeleteFrom = sortBy.indexOf('.');
            sortBy = sortBy.substring(0, toDeleteFrom);
            paging = PageRequest.of(page, pageSize, Sort.by(sortBy).descending());
        } else {
            paging = PageRequest.of(page, pageSize, Sort.by(sortBy));
        }

        Page<Property> properties = propertyRepository.findAllByState(PropertyState.ACTIVE, paging);
        Page<PropertyListItem> pagedProperties = properties.map(PropertyListItem::new);

        PropertyList propertyListWithPages = new PropertyList();
        propertyListWithPages.setTotalItems(propertyRepository.countAllById(PropertyState.ACTIVE));

        if (pagedProperties.hasContent()) {
            propertyListWithPages.setPropertyListItems(pagedProperties.getContent());
        }

        return propertyListWithPages;
    }

    public PropertyList getActiveProperties(Integer page, Integer pageSize, String sortBy, PropertyListFilter propertyListFilter) {
        Pageable paging;
        if (sortBy.contains(".desc")) {
            int toDeleteFrom = sortBy.indexOf('.');
            sortBy = sortBy.substring(0, toDeleteFrom);
            paging = PageRequest.of(page, pageSize, Sort.by(sortBy).descending());
        } else {
            paging = PageRequest.of(page, pageSize, Sort.by(sortBy));
        }

        PropertySearchSpecification.propertyListFilter = propertyListFilter;
        Specification<Property> propertySpecification = PropertySearchSpecification.citySearch()
                .and(PropertySearchSpecification.onlyWithPicture()
                        .and(PropertySearchSpecification.setSqmPriceRange()
                                .and(PropertySearchSpecification.setFloorAreaRange()
                                        .and(PropertySearchSpecification.setNumberOfRoomsRange()
                                                .and(PropertySearchSpecification.isActive()
                                                        .and(PropertySearchSpecification.setPriceRange()))))));

        List<Property> properties = propertyRepository.findAll(propertySpecification, paging).getContent();
        List<PropertyListItem> propertyListItems = properties.stream()
                .map(property -> {
                    List<Upload> imageList = property.getImageList();
                    if (!imageList.isEmpty()) {
                        String smallImageUrl = fileUploader.generateSmallImagePath(imageList.get(0));
                        return new PropertyListItem(property, smallImageUrl);
                    } else {
                        return new PropertyListItem(property);
                    }
                })
                .collect(Collectors.toList());
        Long allItems = propertyRepository.count(propertySpecification);

        PropertyList propertyListWithPages = new PropertyList();
        propertyListWithPages.setPropertyListItems(propertyListItems);
        propertyListWithPages.setTotalItems(allItems);

        return propertyListWithPages;
    }

    public PropertyList getAllPropertiesForAdmin(Integer page, Integer pageSize, String sortBy, String status) {
        Pageable paging;
        if (sortBy.contains(".desc")) {
            int toDeleteFrom = sortBy.indexOf('.');
            sortBy = sortBy.substring(0, toDeleteFrom);
            paging = PageRequest.of(page, pageSize, Sort.by(sortBy).descending().and(Sort.by("id").descending()));
        } else {
            paging = PageRequest.of(page, pageSize, Sort.by(sortBy).and(Sort.by("id")));
        }

        PropertyState propertyState = PropertyState.valueOf(status);
        Page<Property> properties = propertyRepository.findAllByState(propertyState, paging);
        Page<PropertyListItem> pagedProperties = properties.map(PropertyListItem::new);
        PropertyList propertyListWithPages = new PropertyList();
        propertyListWithPages.setTotalItems(propertyRepository.countAllById(propertyState));

        if (pagedProperties.hasContent()) {
            propertyListWithPages.setPropertyListItems(pagedProperties.getContent());
        }
        return propertyListWithPages;
    }

    public PropertyDetails getPropertyDetails(Long id) {
        //Property property = propertyRepository.getOne(id);
        Property property = findPropertyById(id);
        handleNumberOfVisits(property);
        PropertyDetails propertyDetails = null;
        if (property.getState().equals(PropertyState.ACTIVE) || getUserDetails().getAuthorities()
                .stream()
                .anyMatch(ga -> ga.getAuthority()
                        .equals("ROLE_ADMIN"))) {
            propertyDetails = new PropertyDetails(property);
            propertyDetails.setImageList(generateImageListFromUploads(property));
        }

        return propertyDetails;
    }

    private void handleNumberOfVisits(Property property) {
        if (property.getVisits() == null) {
            property.setVisits(0L);
        }
        String emailLoggedInUser = "";
        try {
            emailLoggedInUser = getUserDetails().getUsername();
            if (!property.getAccount().getEmail().equals(emailLoggedInUser)) {
                property.setVisits(property.getVisits() + 1);
            }
        } catch (Exception e) {
            if (emailLoggedInUser.equals("")) {
                property.setVisits(property.getVisits() + 1);
            }
        }
    }

    public PropertyDetails getPropertyDetailsAuthenticated(Long id) {
        Property property = findPropertyById(id);
        if (property.getAccount().getEmail().equals(getUserDetails().getUsername())
                || getUserDetails().getAuthorities()
                .stream()
                .anyMatch(ga -> ga.getAuthority()
                        .equals("ROLE_ADMIN"))) {
            return getPropertyDetails(id);
        } else {
            return null;
        }
    }

    private List<Image> generateImageListFromUploads(Property property) {
        return property.getImageList()
                .stream()
                .map(upload -> {
                    Image image = new Image();
                    image.setId(upload.getId());
                    image.setTitle(upload.getTitle());
                    image.setFilePath(upload.getFilePath());
                    image.setThumbnailPath(fileUploader.generateThumbnailPath(upload));
                    return image;
                })
                .collect(Collectors.toList());
    }

    public PropertyFormInitData getPropertyFormInitData() {
        List<PropertyTypeDTO> propertyTypes = getPropertyTypeDTOList();
        List<PropertyConditionDTO> propertyConditions = getPropertyConditionDTOList();
        List<PropertyHeatingDTO> propertyHeatings = getPropertyHeatingDTOList();
        List<PropertyParkingDTO> propertyParkings = getPropertyParkingDTOList();
        return new PropertyFormInitData(propertyTypes, propertyConditions,
                propertyHeatings, propertyParkings);
    }

    public Long createProperty(PropertyForm propertyForm) {
        Property property = new Property(propertyForm);
        Account accountByEmail = accountService.findAccountByEmail(getUserDetails().getUsername());
        property.setAccount(accountByEmail);
        setLocation(property);
        Property savedProperty = propertyRepository.save(property);
        updateUploads(propertyForm.getImageList(), savedProperty);
        if (getUserDetails().getAuthorities()
                .stream()
                .anyMatch(ga -> ga.getAuthority()
                        .equals("ROLE_OWNER"))) {
            sendAutoGeneratedMessageByPropertyCreation(property);
        }
        return savedProperty.getId();
    }

    private void updateUploads(List<Long> imageList, Property property) {
        for (Long uploadId : imageList) {
            try {
                Upload uploadFromDatabase = fileUploader.findById(uploadId);
                uploadFromDatabase.setProperty(property);
                fileUploader.save(uploadFromDatabase);
            } catch (EntityNotFoundException ex) {
                logger.debug(ex.getMessage());
            }
        }
    }

    private void setLocation(Property property) {
        Location location = mapService.getLocation(property);
        if (location != null) {
            property.setLng(location.getLng());
            property.setLat(location.getLat());
        }
    }

    public boolean activateProperty(Long propertyId) {
        boolean isActivated = false;
        Property propertyById = findPropertyById(propertyId);
        if (propertyById.getAccount().getRole().equals(Role.ROLE_OWNER)
                || propertyById.getAccount().getRole().equals(Role.ROLE_ADMIN)) {
            propertyById.setState(PropertyState.ACTIVE);
            isActivated = true;
            if (!propertyById.getAccount().getEmail().equals(getUserDetails().getUsername())) {
                sendAutoGeneratedMessageByAdminAction(propertyById, ADMIN_ACTION_ACTIVATE);
            }
        }
        return isActivated;
    }

    public void inactivateProperty(Long propertyId) {
        Property propertyById = findPropertyById(propertyId);
        propertyById.setState(PropertyState.INACTIVE);
        if (!propertyById.getAccount().getEmail().equals(getUserDetails().getUsername())) {
            sendAutoGeneratedMessageByAdminAction(propertyById, ADMIN_ACTION_INACTIVATE);
        }
    }

    public boolean deleteProperty(Long propertyId) {
        boolean isSuccessfulDeletion = false;
        String emailLoggedInUser = getUserDetails().getUsername();
        Property property = findPropertyById(propertyId);
        if (property.getAccount().getEmail().equals(emailLoggedInUser) || getUserDetails().getAuthorities()
                .stream()
                .anyMatch(ga -> ga.getAuthority()
                        .equals("ROLE_ADMIN"))) {
            property.setState(PropertyState.INACTIVE);
            isSuccessfulDeletion = true;
            if (property.getAccount().getEmail().equals(emailLoggedInUser)) {
                logger.debug("Property deleted (inactivated) by owner with email {}.", emailLoggedInUser);
            } else {
                sendAutoGeneratedMessageByAdminAction(property, ADMIN_ACTION_INACTIVATE);
                logger.debug("Property deleted (inactivated) by admin with email {}.", emailLoggedInUser);
            }
        }
        return isSuccessfulDeletion;
    }

    public boolean propertyRegistered(Long id) {
        return propertyRepository
                .findById(id)
                .isPresent();
    }

    public Property findPropertyById(Long propertyId) {
        return propertyRepository.findById(propertyId)
                .orElseThrow(() -> new EntityNotFoundException("Property not found with given id: " + propertyId));
    }

    public PropertyListItem findActivePropertyById(Long propertyId) {
        Property property = propertyRepository.findAllByStateAndId(PropertyState.ACTIVE, propertyId)
                .orElseThrow(() -> new EntityNotFoundException("Property not found with given id: " + propertyId));
        return new PropertyListItem(property);
    }

    private List<PropertyTypeDTO> getPropertyTypeDTOList() {
        return Arrays.stream(PropertyType.values())
                .map(PropertyTypeDTO::new)
                .collect(Collectors.toList());
    }

    private List<PropertyConditionDTO> getPropertyConditionDTOList() {
        return Arrays.stream(PropertyCondition.values())
                .map(PropertyConditionDTO::new)
                .collect(Collectors.toList());
    }

    private List<PropertyHeatingDTO> getPropertyHeatingDTOList() {
        return Arrays.stream(PropertyHeating.values())
                .map(PropertyHeatingDTO::new)
                .collect(Collectors.toList());
    }

    private List<PropertyParkingDTO> getPropertyParkingDTOList() {
        return Arrays.stream(PropertyParking.values())
                .map(PropertyParkingDTO::new)
                .collect(Collectors.toList());
    }

    private UserDetails getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetails) authentication.getPrincipal();
    }

    public PropertyDetails updateProperty(Long id, PropertyForm propertyForm) {
        String emailLoggedInUser = getUserDetails().getUsername();
        Property property = findPropertyById(id);
        if (property.getAccount().getEmail().equals(emailLoggedInUser) || getUserDetails().getAuthorities()
                .stream()
                .anyMatch(ga -> ga.getAuthority()
                        .equals("ROLE_ADMIN"))) {
            // TODO: check if address is changed and call setLocation() only in that case
            updatePropertyFields(property, propertyForm);
            updateUploads(propertyForm.getImageList(), property);
            setLocation(property);
            if (property.getAccount().getEmail().equals(emailLoggedInUser)) {
                logger.debug("Property updated by owner with email {}.", emailLoggedInUser);
            } else {
                sendAutoGeneratedMessageByAdminAction(property, ADMIN_ACTION_UPDATE);
                logger.debug("Property updated by admin with email {}.", emailLoggedInUser);
            }
            return new PropertyDetails(property);
        } else {
            return null;
        }
    }

    private void sendAutoGeneratedMessageByAdminAction(Property property, String action) {
        MessageForm messageForm = new MessageForm();
        messageForm.setPropertyId(property.getId());
        messageForm.setToId(property.getAccount().getId());
        Account accountByEmail = accountService.findAccountByEmail(getUserDetails().getUsername());
        messageForm.setContent("Dear " + property.getAccount().getFullName() + ",\n\n" +
                "The property advertisement - " + property.getName() + " -\n" +
                action + "\n" +
                accountByEmail.getFullName() + "\n\n" +
                "Regards,\n" +
                "Moovsmart team \n\n" +
                "This is an auto generated message.");
        messageService.createMessage(messageForm);
        logger.debug("Auto generated message sent to owner with email {}.", property.getAccount().getEmail());
    }

    private void sendAutoGeneratedMessageByPropertyCreation(Property property) {
        List<Account> adminList = accountService.getAdminAccountListForAutoGeneratedMessage();
        MessageForm messageForm = new MessageForm();
        messageForm.setPropertyId(property.getId());
        for (Account adminAccount : adminList) {
            messageForm.setToId(adminAccount.getId());
            messageForm.setContent("Dear " + adminAccount.getFullName() + ",\n\n" +
                    "The property advertisement - " + property.getName() + " - with id: " + property.getId() + "\n" +
                    "has been uploaded by " + "\n" +
                    property.getAccount().getFullName() + "\n" +
                    "and it needs to be activated. \n\n" +
                    "Regards,\n" +
                    property.getAccount().getFullName() + " \n\n" +
                    "This is an auto generated message.");
            messageService.createMessage(messageForm);
        }
        logger.debug("Auto generated message sent to all admin from owner with email {}.",
                property.getAccount().getEmail());
    }

    private void updatePropertyFields(Property property, PropertyForm propertyForm) {
        property.setName(propertyForm.getName());
        property.setNumberOfRooms(propertyForm.getNumberOfRooms());
        property.setPrice(propertyForm.getPrice());
        property.setFloorArea(propertyForm.getFloorArea());
        property.setLotSize(propertyForm.getLotSize());
        property.setBalconySize(propertyForm.getBalconySize());
        property.setYearBuilt(propertyForm.getYearBuilt());
        property.setDescription(propertyForm.getDescription());
        property.setPropertyType(PropertyType.valueOf(propertyForm.getPropertyType()));
        property.setPropertyCondition(PropertyCondition.valueOf(propertyForm.getPropertyCondition()));
        property.setPropertyHeating(PropertyHeating.valueOf(propertyForm.getPropertyHeating()));
        property.setPropertyParking(PropertyParking.valueOf(propertyForm.getPropertyParking()));
        property.setCountry(propertyForm.getCountry());
        property.setZipCode(propertyForm.getZipCode());
        property.setCity(propertyForm.getCity());
        property.setStreet(propertyForm.getStreet());
    }

    public List<String> cityAutoComplete(String keyword) {
        return propertyRepository.searchCity(keyword);
    }

}
