package com.example.webchecker.controller;

import com.example.webchecker.entity.Website;
import com.example.webchecker.repository.WebsiteRepository;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/websites")
public class WebsiteController {

    private final WebsiteRepository websiteRepository;

    public WebsiteController(WebsiteRepository websiteRepository) {
        this.websiteRepository = websiteRepository;
    }

    @PostMapping
    public Website addWebsite(@RequestBody Website website) {
        return websiteRepository.save(website);
    }

    @GetMapping
    public List<Website> getAllWebsites() {
        return websiteRepository.findAll();
    }
}
