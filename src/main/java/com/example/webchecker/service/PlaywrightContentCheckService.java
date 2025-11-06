package com.example.webchecker.service;

import com.example.webchecker.entity.ContentItem;
import com.example.webchecker.entity.Website;
import com.example.webchecker.repository.ContentItemRepository;
import com.example.webchecker.repository.WebsiteRepository;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlaywrightContentCheckService {

    private final WebsiteRepository websiteRepository;
    private final ContentItemRepository contentItemRepository;
    private final MailService mailService;

    public PlaywrightContentCheckService(WebsiteRepository websiteRepository,
                                         ContentItemRepository contentItemRepository,
                                         MailService mailService) {
        this.websiteRepository = websiteRepository;
        this.contentItemRepository = contentItemRepository;
        this.mailService = mailService;
    }

    /**
     * Prüft ALLE Websites, die laut Intervall fällig sind.
     */
    public void checkWebsitesUniversal() {
        List<Website> websites = websiteRepository.findAll();
        LocalDateTime now = LocalDateTime.now();

        for (Website website : websites) {
            if (website.getLastChecked() == null ||
                    website.getLastChecked().plusMinutes(website.getIntervalMinutes()).isBefore(now)) {
                checkSingleWebsite(website);
            }
        }
    }

    /**
     * Prüft nur EINE Website sofort.
     */
    public void checkSingleWebsite(Website website) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(true)
            );
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            try {
                System.out.println("🔎 Prüfe " + website.getName() + " (" + website.getUrl() + ")");
                page.navigate(website.getUrl(), new Page.NavigateOptions().setTimeout(90000));
                page.waitForLoadState(LoadState.NETWORKIDLE);
                page.waitForTimeout(5000);

                List<ElementHandle> elements = page.querySelectorAll("a, h1, h2, h3, h4, h5, h6, p, span, article, div, li, section");
                System.out.println("✅ " + elements.size() + " Elemente gefunden.");

                List<ContentItem> newItems = new ArrayList<>();
                long existingCount = contentItemRepository.countByWebsite(website);

                for (ElementHandle element : elements) {
                    String text = element.innerText().trim();
                    String href = element.getAttribute("href");

                    if (!text.isEmpty() && (href == null || href.startsWith("http"))) {
                        if (href == null) href = website.getUrl();

                        if (!contentItemRepository.existsByContentUrl(href)) {
                            ContentItem newItem = new ContentItem(text, href, website);
                            contentItemRepository.save(newItem);
                            newItems.add(newItem);
                        }
                    }
                }

                website.setLastChecked(LocalDateTime.now());
                websiteRepository.save(website);

                if (existingCount > 0 && !newItems.isEmpty()) {
                    mailService.sendSummaryMail("Empfänger Email-Adresse hier eintragen",
                            website.getName(), newItems);
                }

                System.out.println("🔔 " + newItems.size() + " neue Elemente auf " + website.getName());

            } catch (Exception e) {
                System.err.println("⚠️ Fehler bei " + website.getName() + ": " + e.getMessage());
            } finally {
                browser.close();
            }
        }
    }
}
