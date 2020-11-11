package com.progmasters.moovsmart.controller;

import com.progmasters.moovsmart.dto.FileResource;
import com.progmasters.moovsmart.dto.ImageResource;
import com.progmasters.moovsmart.service.FileUploader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@RestController
@RequestMapping("/api/uploads")
public class UploadController {

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
    private final FileUploader fileUploader;
    @Value("${cloudinary.category}")
    private String category;

    @Autowired
    public UploadController(FileUploader fileUploader) {
        this.fileUploader = fileUploader;
    }


    @PostMapping
    public ResponseEntity<ImageResource> uploadFile(@RequestParam("file") @NotNull @NotBlank @Valid CommonsMultipartFile file) throws IOException {
        String contentType = file.getFileItem().getContentType();
        if ("image/png".equals(contentType) || "image/jpeg".equals(contentType)) {
            ImageResource imageResource = fileUploader.processFile(file, null, category);
            logger.debug("Image is uploaded with id {}", imageResource.getId());
            return new ResponseEntity<>(imageResource, HttpStatus.OK);
        } else {
            logger.debug("Wrong file format for upload");
            return new ResponseEntity<>(null, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUpload(@PathVariable("id") Long id) {
        boolean result = fileUploader.destroyFileById(id);
        if (result) {
            logger.debug("Image with id {} is deleted.", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            logger.debug("No image found with id {}.", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        try {
            FileResource fileResource = fileUploader.getFile(id);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.valueOf(fileResource.getMediaType()))
                    .body(fileResource.getData());
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateFileDescription(@PathVariable Long id, @RequestBody ImageResource imageResource) {
        try {
            fileUploader.updateFileDescription(id, imageResource);
            logger.debug("Image description with id {} is updated.", id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
