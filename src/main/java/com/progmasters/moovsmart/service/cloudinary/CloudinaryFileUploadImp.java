package com.progmasters.moovsmart.service.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.progmasters.moovsmart.domain.Upload;
import com.progmasters.moovsmart.dto.FileResource;
import com.progmasters.moovsmart.repository.UploadRepository;
import com.progmasters.moovsmart.service.FileUploader;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

@Service
@Transactional
public class CloudinaryFileUploadImp extends FileUploader {
    private static final String TRANSFORMATION = "media_lib_thumb";

    private static final Logger logger = LoggerFactory.getLogger(CloudinaryFileUploadImp.class);
    private final Cloudinary cloudinary;

    public CloudinaryFileUploadImp(UploadRepository uploadRepository, Cloudinary cloudinary) {
        super(uploadRepository);
        this.cloudinary = cloudinary;
    }

    @Override
    protected String generateThumbnailPath(Upload upload) {
        return cloudinary.url().transformation(new Transformation().named(TRANSFORMATION)).secure(true).generate(upload.getPublicId());
    }

    @Override
    protected String generateSmallImagePath(Upload upload) {
        return cloudinary.url().transformation(new Transformation().gravity("center").height(300).width(300).crop("fill")).secure(true).generate(upload.getPublicId());
    }

    @Override
    protected Upload storeFile(CommonsMultipartFile commonsMultipartFile, String category) {
        Map params = ObjectUtils.asMap(
                "folder", category,
                "overwrite", false,
                "resource_type", "auto",
                "use_filename", true);
        UploadResponse uploadResponse;
        File fileToUpload = new File(System.getProperty("java.io.tmpdir") + '/' + commonsMultipartFile.getOriginalFilename());
        try {
            commonsMultipartFile.transferTo(fileToUpload);
            uploadResponse = new ObjectMapper()
                    .convertValue(cloudinary.uploader().upload(fileToUpload, params), UploadResponse.class);
        } catch (IOException e) {
            throw new CloudinaryUploadException();
        }

        return new Upload(uploadResponse, commonsMultipartFile);
    }

    @Override
    protected boolean destroyFileFromStorage(Upload upload) {
        boolean result = false;
        try {
            Map response = cloudinary.uploader().destroy(upload.getPublicId(), ObjectUtils.emptyMap());
            if ("ok".equals(response.get("result"))) {
                result = true;
                logger.debug("Image with id {} deleted from Cloudinary.", upload.getId());
            } else {
                logger.debug("Unable to delete image with id {}: {}.", upload.getId(), response.get("result"));
            }
        } catch (IOException e) {
            throw new CloudinaryUploadException();
        }
        return result;
    }

    @Override
    public FileResource getFile(Long id) {
        Upload upload = findById(id);
        String url = upload.getFilePath();
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream())) {
            byte[] bytes = IOUtils.toByteArray(in);
            return new FileResource(bytes, upload.getMediaType(), upload.getOriginalFileName());
        } catch (IOException e) {
            throw new CloudinaryDownloadException();
        }
    }

}
