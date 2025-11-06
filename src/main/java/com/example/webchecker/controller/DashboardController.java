package com.example.webchecker.controller;

import com.example.webchecker.entity.Website;
import com.example.webchecker.entity.ContentItem;
import com.example.webchecker.repository.WebsiteRepository;
import com.example.webchecker.repository.ContentItemRepository;
import com.example.webchecker.service.PlaywrightContentCheckService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DashboardController {

    private final WebsiteRepository websiteRepository;
    private final ContentItemRepository contentItemRepository;
    private final PlaywrightContentCheckService playwrightService;

    public DashboardController(WebsiteRepository websiteRepository,
                               ContentItemRepository contentItemRepository,
                               PlaywrightContentCheckService playwrightService) {
        this.websiteRepository = websiteRepository;
        this.contentItemRepository = contentItemRepository;
        this.playwrightService = playwrightService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Website> websites = websiteRepository.findAll();
        List<ContentItem> contents = contentItemRepository.findAll();

        model.addAttribute("websites", websites);
        model.addAttribute("contents", contents);
        model.addAttribute("newWebsite", new Website());
        return "index";
    }

    @PostMapping("/addWebsite")
    public String addWebsite(@ModelAttribute Website website) {
        websiteRepository.save(website);
        return "redirect:/";
    }

    @PostMapping("/checkNow")
    public String checkAllWebsites() {
        playwrightService.checkWebsitesUniversal();
        return "redirect:/";
    }


    @PostMapping("/checkAll")
    public String checkAll() {
        playwrightService.checkWebsitesUniversal();
        return "redirect:/";
    }


    @PostMapping("/checkWebsite/{id}")
    public String checkSingleWebsite(@PathVariable Long id) {
        websiteRepository.findById(id).ifPresent(playwrightService::checkSingleWebsite);
        return "redirect:/";
    }

    @PostMapping("/updateInterval/{id}")
    public String updateInterval(@PathVariable Long id, @RequestParam("interval") int interval) {
        websiteRepository.findById(id).ifPresent(site -> {
            site.setIntervalMinutes(interval);
            websiteRepository.save(site);
        });
        return "redirect:/";
    }

    @PostMapping("/deleteWebsite/{id}")
    public String deleteWebsite(@PathVariable Long id) {
        contentItemRepository.deleteAll(contentItemRepository.findByWebsiteId(id));
        websiteRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/deleteContent/{id}")
    public String deleteContent(@PathVariable Long id) {
        contentItemRepository.deleteById(id);
        return "redirect:/";
    }
}