package com.example.webchecker.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Website {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String url;
    private String cssSelector;

    @Column(name = "last_checked")
    private LocalDateTime lastChecked;

    @Column(name = "interval_minutes")
    private int intervalMinutes = 5;

    @OneToMany(mappedBy = "website", cascade = CascadeType.ALL)
    private List<ContentItem> contentItems;


    public Website() {}

    public Website(String name, String url, String cssSelector) {
        this.name = name;
        this.url = url;
        this.cssSelector = cssSelector;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getCssSelector() {
        return cssSelector;
    }
    public void setCssSelector(String cssSelector) {
        this.cssSelector = cssSelector;
    }

    public List<ContentItem> getContentItems() {
        return contentItems;
    }
    public void setContentItems(List<ContentItem> contentItems) {
        this.contentItems = contentItems;
    }

    public LocalDateTime getLastChecked() {
        return lastChecked;
    }
    public void setLastChecked(LocalDateTime lastChecked) {
        this.lastChecked = lastChecked;
    }

    public int getIntervalMinutes() {
        return intervalMinutes;
    }
    public void setIntervalMinutes(int intervalMinutes) {
        this.intervalMinutes = intervalMinutes;
    }
}
