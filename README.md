# 🌐 WebChecker — Smart Website Change Monitor

WebChecker is a **Spring Boot** web application that automatically monitors websites for content changes.  
It uses **Playwright** to fetch and analyze dynamic web content, stores data in an **H2 in-memory database**,  
and sends **email notifications** when updates are detected.

## 🚀 Features

- 🕵️ Detects changes in websites automatically  
- 🧠 Uses Playwright to handle JavaScript-rendered content  
- 💌 Sends notification emails when new content appears  
- 🕒 User-defined check intervals (per website)  
- 🖥️ Modern responsive web dashboard built with Bootstrap 5  
- 💾 Stores all data in an embedded H2 database  

## 🧩 Tech Stack

| Component | Technology |
|------------|-------------|
| Backend | Java 17, Spring Boot |
| Frontend | Thymeleaf, Bootstrap 5 |
| Email | Spring Mail (SMTP) |
| Database | H2 (in-memory) |
| Web Scraping | Playwright for Java |

## ⚙️ Setup Instructions

###  Clone the repository
``bash
git clone https://github.com/yourusername/webchecker.git
cd webchecker

---

### ✉️  Email Configuration
``markdown
###  Configure Email Settings

Before running the project, open `src/main/resources/application.properties`  
and update the following fields:

``properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=YOUR_SENDER_EMAIL@gmail.com
spring.mail.password=YOUR_APP_PASSWORD
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
⚠️ Important Notes:

* Do not use your real email password!
Use an App Password generated in your Google Account security settings.
Example: https://myaccount.google.com/apppasswords

* You must also open the Email Service class (MailService or EmailService)
and set the sender and receiver email addresses manually before compiling.


---

### ▶️  Running the App
``markdown
###  Run the Project

Run the Spring Boot application using Maven or your IDE:

``bash
mvn spring-boot:run

Then open your browser at:

http://localhost:8080

---

### 🧠  Usage
``markdown
## 🧠 Usage

1. Add websites you want to monitor via the dashboard.  
2. Specify how often each website should be checked (interval in minutes).  
3. Click **“Jetzt prüfen”** to trigger a scan manually.  
4. You’ll receive an **email notification** when a new or changed entry is detected.  
5. Use the delete buttons to remove websites or detected content items.

## 🧰 Development Notes

- The project uses **H2 in-memory DB**, so all data resets after restart.  
- You can enable the H2 console for debugging via:
http://localhost:8080/h2-console

- The app uses a built-in **scheduler** for periodic checks.  
Each website’s interval can be configured in the web UI.

## 📧 Email Sending Troubleshooting

| Problem | Solution |
|----------|-----------|
| Authentication failed | Make sure you use an **App Password** (not your normal password) |
| Gmail blocks the connection | Enable **2-Step Verification** and generate an app password |
| Email not received | Check spam folder or try a different receiver address |
| Want to avoid Gmail limits | You can use [Postal](https://github.com/postalserver/postal) — an open source mail server |

---

You are free to use, modify, and distribute this project with attribution.

---

### ✨ Created by Alae Ben Salah
