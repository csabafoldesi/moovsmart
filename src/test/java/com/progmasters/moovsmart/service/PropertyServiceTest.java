package com.progmasters.moovsmart.service;

import com.progmasters.moovsmart.domain.*;
import com.progmasters.moovsmart.dto.AccountCreateCommand;
import com.progmasters.moovsmart.dto.PropertyForm;
import com.progmasters.moovsmart.dto.PropertyList;
import com.progmasters.moovsmart.repository.PropertyRepository;
import com.progmasters.moovsmart.security.UserDetailsImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PropertyServiceTest {

    private PropertyForm propertyForm;
    private PropertyService propertyService;
    private Account ownerAccount;
    private UserDetails ownerUserDetails;

    @Mock
    private PropertyRepository propertyRepositoryMock;

    @Mock
    private FileUploader fileUploaderMock;

    @Mock
    private AccountService accountServiceMock;

    @Mock
    private MapService mapServiceMock;

    @Mock
    private MessageService messageServiceMock;

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

        this.propertyService = new PropertyService(propertyRepositoryMock, fileUploaderMock, accountServiceMock, mapServiceMock, messageServiceMock);

        AccountCreateCommand accountCreateCommand = new AccountCreateCommand();
        accountCreateCommand.setFullName("moovsmart");
        accountCreateCommand.setEmail("moovsmartproject2020.1@gmail.com");
        accountCreateCommand.setPassword("moovsmart");
        accountCreateCommand.setRole(Role.ROLE_OWNER.toString());
        this.ownerAccount = new Account(accountCreateCommand);
        this.ownerUserDetails = new UserDetailsImpl(this.ownerAccount);
        Authentication auth = new UsernamePasswordAuthenticationToken(this.ownerUserDetails, null);

        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Test
    public void testCreateProperty() {
        when(accountServiceMock.findAccountByEmail(this.ownerUserDetails.getUsername()))
                .thenReturn(this.ownerAccount);
        when(propertyRepositoryMock.save(any(Property.class))).thenAnswer(i -> {
            Property property = (Property) i.getArguments()[0];
            property.setId(1L);
            return property;
        });

        Long savedPropertyId = propertyService.createProperty(this.propertyForm);
        assertEquals(1, savedPropertyId);

        verify(accountServiceMock, times(1)).findAccountByEmail(
                this.ownerUserDetails.getUsername());
        verify(propertyRepositoryMock, times(1)).save(any(Property.class));
        verify(accountServiceMock, times(1)).getAdminAccountListForAutoGeneratedMessage();
        verifyNoMoreInteractions(accountServiceMock);
        verifyNoMoreInteractions(propertyRepositoryMock);
    }

    @Test
    void testFindAllPropertyTwoItems() {
        Pageable paging = PageRequest.of(0, 5, Sort.by("id"));

        List<Property> propertyList = new ArrayList<>();
        PropertyForm propertyForm = new PropertyForm();
        propertyForm.setName("Cosy apartment");
        propertyForm.setNumberOfRooms(3);
        propertyForm.setPrice(100000);
        propertyForm.setFloorArea(120);
        Property property = new Property(propertyForm);
        property.setId(1L);
        propertyList.add(property);
        propertyForm = new PropertyForm();
        propertyForm.setName("Cosy apartment 1");
        propertyForm.setNumberOfRooms(4);
        propertyForm.setPrice(200000);
        propertyForm.setFloorArea(150);
        property = new Property(propertyForm);
        property.setId(2L);
        propertyList.add(property);

        Page<Property> propertyPages = new PageImpl<>(propertyList);
        when(propertyRepositoryMock.findAllByState(PropertyState.ACTIVE, paging)).thenReturn(propertyPages);
        when(propertyRepositoryMock.countAllById(PropertyState.ACTIVE)).thenReturn((long) propertyList.size());

        PropertyList properties = propertyService.getActiveProperties(0, 5, "id");
        assertEquals(2, properties.getTotalItems());
        assertEquals(2, properties.getPropertyListItems().size());
        assertEquals(1L, properties.getPropertyListItems().get(0).getId());
        assertEquals(2L, properties.getPropertyListItems().get(1).getId());
    }

    @Test
    void testFindAllPaging() {
        int page = 1;
        int size = 4;
        String sortBy = "id";
        List<Property> propertyList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            PropertyForm propertyForm = new PropertyForm();
            propertyForm.setName("Name" + i);
            propertyForm.setNumberOfRooms(3);
            propertyForm.setPrice(100000);
            propertyForm.setFloorArea(120);
            Property property = new Property(propertyForm);
            property.setId(i + 1L);
            propertyList.add(property);
        }
        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, propertyList.size());
        List<Property> propertyListPaged = propertyList.subList(fromIndex, toIndex);
        Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Property> propertyPages = new PageImpl<>(propertyListPaged);
        when(propertyRepositoryMock.findAllByState(PropertyState.ACTIVE, paging)).thenReturn(propertyPages);
        when(propertyRepositoryMock.countAllById(PropertyState.ACTIVE)).thenReturn((long) propertyList.size());

        PropertyList properties = propertyService.getActiveProperties(page, size, sortBy);
        assertEquals(10, properties.getTotalItems());
        assertEquals(4, properties.getPropertyListItems().size());
        assertEquals(5L, properties.getPropertyListItems().get(0).getId());
        assertEquals(6L, properties.getPropertyListItems().get(1).getId());

    }

    @Test
    public void testDeletePropertySuccessfully() {
        Property property = new Property(this.propertyForm);
        property.setAccount(this.ownerAccount);
        when(propertyRepositoryMock.findById(1L)).thenReturn(Optional.of(property));

        boolean isSuccessfulDeletion = propertyService.deleteProperty(1L);
        assertTrue(isSuccessfulDeletion);
        assertEquals(PropertyState.INACTIVE, property.getState());
    }

    @Test
    public void testDeletePropertyUnSuccessfullyNotOwnProperty() {
        Property property = new Property(this.propertyForm);
        property.setState(PropertyState.ACTIVE);
        AccountCreateCommand accountCreateCommand = new AccountCreateCommand();
        accountCreateCommand.setFullName("other");
        accountCreateCommand.setEmail("other@gmail.com");
        accountCreateCommand.setPassword("other");
        accountCreateCommand.setRole(Role.ROLE_OWNER.toString());
        Account account = new Account(accountCreateCommand);
        property.setAccount(account);
        when(propertyRepositoryMock.findById(1L)).thenReturn(Optional.of(property));

        boolean isSuccessfulDeletion = propertyService.deleteProperty(1L);
        assertFalse(isSuccessfulDeletion);
        assertEquals(PropertyState.ACTIVE, property.getState());
    }

    @Test
    public void testDeletePropertyWhenNotFound() {
        Property property = new Property(this.propertyForm);
        property.setAccount(this.ownerAccount);

        Exception exception = Assertions.assertThrows(EntityNotFoundException.class,
                () -> propertyService.deleteProperty(1L));
        Assertions.assertEquals("Property not found with given id: 1", exception.getMessage());
    }
}
