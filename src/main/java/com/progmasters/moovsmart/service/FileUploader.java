package com.progmasters.moovsmart.service;

import com.progmasters.moovsmart.domain.Upload;
import com.progmasters.moovsmart.domain.UploadState;
import com.progmasters.moovsmart.dto.FileResource;
import com.progmasters.moovsmart.dto.ImageResource;
import com.progmasters.moovsmart.repository.UploadRepository;
import com.progmasters.moovsmart.service.cloudinary.CloudinaryFileUploadImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public abstract class FileUploader {

    private static final Logger logger = LoggerFactory.getLogger(CloudinaryFileUploadImp.class);
    protected UploadRepository uploadRepository;

    @Autowired
    public FileUploader(UploadRepository uploadRepository) {
        this.uploadRepository = uploadRepository;
    }

    public ImageResource processFile(CommonsMultipartFile commonsMultipartFile, String title, String category) throws IOException {
        Upload upload = storeFile(commonsMultipartFile, category);
        upload.setTitle(title);
        upload.setCategory(category);
        ImageResource imageResource = new ImageResource(uploadRepository.save(upload));
        imageResource.setThumbnailPath(generateThumbnailPath(upload));
        return imageResource;
    }

    protected abstract String generateThumbnailPath(Upload upload);

    protected abstract String generateSmallImagePath(Upload upload);

    protected abstract Upload storeFile(CommonsMultipartFile commonsMultipartFile, String category) throws IOException;

    public boolean destroyFileById(Long uploadId) {
        boolean deleteFromStorageIsSuccess = false;
        try {
            Upload upload = findById(uploadId);
            if (upload.getProperty() != null) {
                upload.setState(UploadState.DELETED);
                uploadRepository.save(upload);
                deleteFromStorageIsSuccess = true;
            } else {
                deleteFromStorageIsSuccess = destroyFileFromStorage(upload);
                if (deleteFromStorageIsSuccess) {
                    uploadRepository.delete(upload);
                }
            }
        } catch (EntityNotFoundException ex) {
            logger.debug(ex.getMessage());
        }
        return deleteFromStorageIsSuccess;
    }

    protected abstract boolean destroyFileFromStorage(Upload upload);

    public abstract FileResource getFile(Long id);

    public Upload findById(Long id) {
        return uploadRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Upload found with given Id: " + id));
    }

    public void save(Upload upload) {
        this.uploadRepository.save(upload);
    }

    public List<Upload> getFileRegistryList() {
        return uploadRepository.findAll();
    }

    // we only update the title field for now
    public void updateFileDescription(Long id, ImageResource imageResource) {
        Upload upload = findById(id);
        upload.setTitle(imageResource.getTitle());
        uploadRepository.save(upload);
    }
}
