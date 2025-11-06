package com.example.webchecker.entity;

import jakarta.persistence.*;

@Entity
public class ContentItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String contentText;

    private String contentUrl;

    @ManyToOne
    @JoinColumn(name = "website_id")
    private Website website;

    public ContentItem() {}

    public ContentItem(String contentText, String contentUrl, Website website) {
        this.contentText = contentText;
        this.contentUrl = contentUrl;
        this.website = website;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public Website getWebsite() {
        return website;
    }

    public void setWebsite(Website website) {
        this.website = website;
    }
}