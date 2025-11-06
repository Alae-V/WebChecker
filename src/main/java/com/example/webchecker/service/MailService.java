package com.example.webchecker.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.example.webchecker.entity.ContentItem;

import java.util.List;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    /**
     * Schickt eine zusammengefasste E-Mail mit mehreren neuen Inhalten.
     */
    public void sendSummaryMail(String to, String websiteName, List<ContentItem> newItems) {
        if (newItems.isEmpty()) return;

        StringBuilder contentBuilder = new StringBuilder();
        for (ContentItem item : newItems) {
            contentBuilder.append("- ").append(item.getContentText())
                    .append("\n").append(item.getContentUrl())
                    .append("\n\n");
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("🆕 Neue Inhalte auf " + websiteName);
        message.setText("Auf der Website " + websiteName + " wurden neue Inhalte gefunden:\n\n"
                + contentBuilder.toString());

        mailSender.send(message);
        System.out.println("📧 Zusammengefasste Mail gesendet an " + to);
    }
}
