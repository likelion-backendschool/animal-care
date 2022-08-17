package com.codelion.animalcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AnimalCareApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnimalCareApplication.class, args);
	}

}
