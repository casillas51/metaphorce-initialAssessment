package com.boosters.initial.assessment.SecureService.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The ConfigurationTest class.
 */
@Configuration
@ComponentScan(basePackages = "com.boosters.initial.assessment.SecureService")
@EnableJpaRepositories(basePackages = "com.boosters.initial.assessment.SecureService.repository")
@EntityScan(basePackages = "com.boosters.initial.assessment.SecureService.model.entity")
public class ConfigurationTest {

}
