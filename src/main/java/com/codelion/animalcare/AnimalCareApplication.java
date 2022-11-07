package com.codelion.animalcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@EnableJpaAuditing
@SpringBootApplication
@EnableWebSocket
public class AnimalCareApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnimalCareApplication.class, args);
	}

}
