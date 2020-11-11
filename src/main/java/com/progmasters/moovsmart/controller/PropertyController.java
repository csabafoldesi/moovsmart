package com.progmasters.moovsmart.controller;

import com.progmasters.moovsmart.dto.*;
import com.progmasters.moovsmart.service.BatchUploadService;
import com.progmasters.moovsmart.service.PropertyService;
import com.progmasters.moovsmart.validation.PropertyFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    private static final Logger logger = LoggerFactory.getLogger(PropertyController.class);
    private final PropertyService propertyService;
    private final BatchUploadService batchUploadService;
    private final PropertyFormValidator propertyFormValidator;

    @Autowired
    public PropertyController(PropertyService propertyService, BatchUploadService batchUploadService, PropertyFormValidator propertyFormValidator) {
        this.propertyService = propertyService;
        this.batchUploadService = batchUploadService;
        this.propertyFormValidator = propertyFormValidator;
    }

    @InitBinder("propertyForm")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(propertyFormValidator);
    }

    @GetMapping("/filtered")
    public ResponseEntity<PropertyList> getAllActiveProperties(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            PropertyListFilter propertyListFilter
    ) {
        logger.debug("Property list page requested (active properties).");
        return new ResponseEntity<>(propertyService.getActiveProperties(page, pageSize, sortBy, propertyListFilter), HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/admin")
    public ResponseEntity<PropertyList> getAllPropertiesForAdmin(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam String status
    ) {
        logger.debug("Property list page requested (pending properties for admin page).");
        return new ResponseEntity<>(propertyService.getAllPropertiesForAdmin(
                page, pageSize, sortBy, status), HttpStatus.OK);
    }

    @GetMapping("/filtered/{id}")
    public ResponseEntity<PropertyDetails> getPropertyDetails(@PathVariable("id") Long id) {
        logger.debug("Property details page requested with property id {}.", id);
        try {
            PropertyDetails propertyDetails = propertyService.getPropertyDetails(id);
            if (propertyDetails != null) {
                logger.debug("Property details successfully fetched with property id {}.", id);
                return new ResponseEntity<>(propertyDetails, HttpStatus.OK);
            } else {
                logger.debug("Property details requested for inactive property " +
                        "or insufficient authority with property id {}.", id);
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (EntityNotFoundException ex) {
            logger.debug(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Secured({"ROLE_OWNER", "ROLE_ADMIN"})
    @GetMapping("/for-update/{id}")
    public ResponseEntity<PropertyDetails> getPropertyDetailsForEdit(@PathVariable("id") Long id) {
        try {
            PropertyDetails propertyDetails = propertyService.getPropertyDetailsAuthenticated(id);
            if (propertyDetails != null) {
                logger.debug("Load property details for update {}.", id);
                return new ResponseEntity<>(propertyDetails, HttpStatus.OK);
            } else {
                logger.error("Try to get details for update not owned property {}.", id);
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (EntityNotFoundException ex) {
            logger.debug(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/property-form-init-data")
    public ResponseEntity<PropertyFormInitData> getPropertyFormInitData() {
        logger.debug("Property form initial data requested.");
        return new ResponseEntity<>(propertyService.getPropertyFormInitData(), HttpStatus.OK);
    }

    @Secured({"ROLE_OWNER", "ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<Void> createProperty(@RequestBody @Valid PropertyForm propertyForm) {
        logger.debug("Property creation requested...");
        Long propertyId = propertyService.createProperty(propertyForm);
        logger.debug("Property successfully created with id {}", propertyId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/admin/property-activation/{id}")
    public ResponseEntity<Void> activateProperty(@PathVariable("id") Long id) {
        if (propertyService.activateProperty(id)) {
            logger.debug("Property (pending or inactive) has been activated with property id {}.", id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            logger.debug("Property (pending or inactive) has not been activated with property id {}. " +
                    "Account type may have changed to visitor.", id);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/admin/property-inactivation/{id}")
    public ResponseEntity<Void> inactivateProperty(@PathVariable("id") Long id) {
        propertyService.inactivateProperty(id);
        logger.debug("Property has been inactivated with property id {}.", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured({"ROLE_OWNER", "ROLE_ADMIN"})
    @PutMapping("/{id}")
    public ResponseEntity<PropertyDetails> updateProperty(@PathVariable("id") Long id, @RequestBody @Valid PropertyForm propertyForm) {
        try {
            PropertyDetails propertyDetails = propertyService.updateProperty(id, propertyForm);
            if (propertyDetails != null) {
                logger.debug("Property {} is updated successfully.", id);
                return new ResponseEntity<>(propertyDetails, HttpStatus.OK);
            } else {
                logger.error("Try to update not owned property {}.", id);
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (EntityNotFoundException ex) {
            logger.debug(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Secured({"ROLE_OWNER", "ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable("id") Long id) {
        if (propertyService.deleteProperty(id)) {
            logger.debug("Property deleted (inactivated) with id {}", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            logger.debug("Unable to delete property with id {}", id);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/city")
    public ResponseEntity<List<String>> searchCity(@RequestParam(defaultValue = "") String keyword) {
        return new ResponseEntity<>(propertyService.cityAutoComplete(keyword), HttpStatus.OK);
    }

    @Secured({"ROLE_OWNER", "ROLE_ADMIN"})
    @GetMapping("/batch")
    public ResponseEntity<byte[]> downloadFileForBatchUpload() {
        byte[] response = batchUploadService.getFileForBatchUpload();
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header("Content-Disposition", "attachment; filename=moovsmart.xlsx")
                .body(response);
    }

    @Secured({"ROLE_OWNER", "ROLE_ADMIN"})
    @PostMapping("/batch")
    public ResponseEntity<BatchLoadResult> uploadFileForBatchUpload(@RequestParam("file") @NotNull @NotBlank @Valid MultipartFile file) {
        BatchLoadResult result = batchUploadService.parseUploadedFile(file);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
