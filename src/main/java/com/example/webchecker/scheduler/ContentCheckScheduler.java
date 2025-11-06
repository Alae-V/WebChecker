package com.example.webchecker.scheduler;

import com.example.webchecker.service.PlaywrightContentCheckService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ContentCheckScheduler {

    private final PlaywrightContentCheckService playwrightService;

    public ContentCheckScheduler(PlaywrightContentCheckService playwrightService) {
        this.playwrightService = playwrightService;
    }

    // Prüft jede Minute, ob eine Website laut Intervall fällig ist
    @Scheduled(fixedRate = 60 * 1000)
    public void check() {
        playwrightService.checkWebsitesUniversal();
    }
}
