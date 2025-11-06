package com.example.webchecker.repository;

import java.util.List;
import com.example.webchecker.entity.ContentItem;
import com.example.webchecker.entity.Website;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentItemRepository extends JpaRepository<ContentItem, Long> {
    boolean existsByContentUrl(String contentUrl);
    long countByWebsite(Website website);
    List<ContentItem> findByWebsiteId(Long websiteId);


}
