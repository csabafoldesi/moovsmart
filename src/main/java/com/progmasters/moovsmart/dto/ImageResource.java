package com.progmasters.moovsmart.dto;

import com.progmasters.moovsmart.domain.Upload;

import java.time.LocalDateTime;

public class ImageResource {

    private Long id;
    private String mediaType;
    private String originalFileName;
    private String filePath;
    private String thumbnailPath;
    private Long fileSize;
    private String category;
    private String title;
    private String publicId;
    private LocalDateTime timestamp;

    public ImageResource() {
    }

    public ImageResource(Upload upload) {
        this.id = upload.getId();
        this.mediaType = upload.getMediaType();
        this.originalFileName = upload.getOriginalFileName();
        this.filePath = upload.getFilePath();
        this.fileSize = upload.getFileSize();
        this.category = upload.getCategory();
        this.title = upload.getTitle();
        this.publicId = upload.getPublicId();
        this.timestamp = upload.getUploadDateTime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }
}
