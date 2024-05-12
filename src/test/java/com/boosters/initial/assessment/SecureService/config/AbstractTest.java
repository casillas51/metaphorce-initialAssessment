package com.boosters.initial.assessment.SecureService.config;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

/**
 * The AbstractTest class.
 */
@SpringBootTest
@ActiveProfiles("Test")
@ContextConfiguration(classes = ConfigurationTest.class)
public class AbstractTest {

}
