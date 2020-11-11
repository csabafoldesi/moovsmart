package com.progmasters.moovsmart.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.progmasters.moovsmart.domain.PropertyCondition;
import com.progmasters.moovsmart.domain.PropertyHeating;
import com.progmasters.moovsmart.domain.PropertyParking;
import com.progmasters.moovsmart.domain.PropertyType;
import com.progmasters.moovsmart.dto.PropertyForm;
import com.progmasters.moovsmart.exception.GlobalExceptionHandler;
import com.progmasters.moovsmart.service.BatchUploadService;
import com.progmasters.moovsmart.service.PropertyService;
import com.progmasters.moovsmart.validation.PropertyFormValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class PropertyControllerTest {

    private PropertyForm propertyForm;
    private MockMvc mockMvc;
    private PropertyController propertyController;

    @Autowired
    private PropertyFormValidator propertyFormValidator;

    @Mock
    private PropertyService propertyServiceMock;

    @Mock
    private BatchUploadService batchUploadServiceMock;

    @Mock
    private LocaleResolver localeResolverMock;

    @Mock
    private HttpServletRequest requestMock;

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

        this.propertyController = new PropertyController(propertyServiceMock, batchUploadServiceMock, propertyFormValidator);

        //when(localeResolverMock.resolveLocale(requestMock)).thenReturn(Locale.ENGLISH);
        //when(localeResolverMock.resolveLocale(requestMock)).thenReturn(new Locale("hu"));
        when(localeResolverMock.resolveLocale(requestMock)).thenReturn(new Locale("en"));

        this.mockMvc = MockMvcBuilders.standaloneSetup(propertyController)
                .setControllerAdvice(new GlobalExceptionHandler(messageSource(), localeResolverMock, requestMock))
                .build();
    }

    @AfterEach
    public void validate() {
        validateMockitoUsage();
    }

    @Test
    public void testCreatePropertyWithValidFormData() throws Exception {
        when(propertyServiceMock.createProperty(any(PropertyForm.class))).thenReturn(1L);
        this.mockMvc.perform(post("/api/properties")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(this.propertyForm)))
                .andExpect(status().isCreated());

        verify(propertyServiceMock, times(1)).createProperty(any(PropertyForm.class));
        verifyNoMoreInteractions(propertyServiceMock);
    }

    @Test
    public void testCreatePropertyWithInValidFormData() throws Exception {
        this.propertyForm.setPrice(-1);

        this.mockMvc.perform(post("/api/properties")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(this.propertyForm)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.fieldErrors", hasSize(1)))
                .andExpect(jsonPath("$.fieldErrors[0].field", is("price")))
                .andExpect(jsonPath("$.fieldErrors[0].message", is("Price must be between 1 and 10.000.000 euro!")));

        verifyNoMoreInteractions(propertyServiceMock);
    }

    @Test
    public void testDeletePropertySuccessfully() throws Exception {
        when(propertyServiceMock.deleteProperty(1L)).thenReturn(true);
        this.mockMvc.perform(delete("/api/properties/1"))
                .andExpect(status().isNoContent());

        verify(propertyServiceMock, times(1)).deleteProperty(1L);
        verifyNoMoreInteractions(propertyServiceMock);
    }

    @Test
    public void testDeletePropertyUnSuccessfully() throws Exception {
        when(propertyServiceMock.deleteProperty(1L)).thenReturn(false);
        this.mockMvc.perform(delete("/api/properties/1"))
                .andExpect(status().isBadRequest());

        verify(propertyServiceMock, times(1)).deleteProperty(1L);
        verifyNoMoreInteractions(propertyServiceMock);
    }

    private MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
