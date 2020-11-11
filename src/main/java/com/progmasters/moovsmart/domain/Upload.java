package com.progmasters.moovsmart.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.progmasters.moovsmart.service.cloudinary.UploadResponse;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "upload")
public class Upload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "public_id")
    private String publicId;

    @Column(name = "original_file_name")
    private String originalFileName;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "media_type")
    private String mediaType;

    @Column(name = "title")
    private String title;

    @Column(name = "category")
    private String category = "default";

    @Column(name = "upload_datetime")
    @JsonFormat(locale = "hu", shape = JsonFormat.Shape.STRING, pattern = "yyyy. MM. dd. HH:mm:ss (Z)")
    private LocalDateTime uploadDateTime = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private UploadState state = UploadState.ACTIVE;

    public Upload() {

    }

    public Upload(UploadResponse uploadResponse, CommonsMultipartFile commonsMultipartFile) {
        this.filePath = uploadResponse.getSecureUrl();
        this.originalFileName = commonsMultipartFile.getFileItem().getName();
        this.fileSize = uploadResponse.getBytes();
        this.mediaType = commonsMultipartFile.getContentType();
        this.publicId = uploadResponse.getPublicId();
    }

    /*public Upload(String fullFilePath, long size, String contentType, String originalFileName) {
        this.filePath = fullFilePath;
        this.fileSize = size;
        this.mediaType = contentType;
        this.originalFileName = originalFileName;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getUploadDateTime() {
        return uploadDateTime;
    }

    public void setUploadDateTime(LocalDateTime uploadDateTime) {
        this.uploadDateTime = uploadDateTime;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public UploadState getState() {
        return state;
    }

    public void setState(UploadState state) {
        this.state = state;
    }
}
