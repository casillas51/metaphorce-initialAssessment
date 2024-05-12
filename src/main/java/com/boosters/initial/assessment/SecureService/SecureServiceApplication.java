package com.boosters.initial.assessment.SecureService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The SecureServiceApplication class.
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.boosters.initial.assessment.SecureService.api.repository")
@EntityScan(basePackages = "com.boosters.initial.assessment.SecureService.api.model.entity")
public class SecureServiceApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(SecureServiceApplication.class, args);
	}

}
