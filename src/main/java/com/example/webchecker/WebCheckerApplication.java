package com.example.webchecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class         WebCheckerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebCheckerApplication.class, args);
	}

}
