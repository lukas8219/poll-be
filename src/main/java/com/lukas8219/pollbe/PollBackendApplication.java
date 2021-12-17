package com.lukas8219.pollbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PollBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PollBackendApplication.class, args);
	}

}
