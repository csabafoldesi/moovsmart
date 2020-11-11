package com.progmasters.moovsmart.dto;

public class Image {
    private Long id;
    private String filePath;
    private String thumbnailPath;
    private String title;

    public Image() {
    }

    public Image(ImageResource imageResource) {
        this.id = imageResource.getId();
        this.filePath = imageResource.getFilePath();
        this.thumbnailPath = imageResource.getThumbnailPath();
        this.title = imageResource.getTitle();
    }

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

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
