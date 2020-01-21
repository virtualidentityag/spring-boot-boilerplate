package com.myCompanyName.myProjectName;

import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ActiveProfiles("integration_test")
@TestPropertySource(locations = "classpath:application-integration_tests.properties")
@SpringBootTest
@Tag("integration")
@DirtiesContext
public abstract class IntegrationTests extends AbstractJUnit4SpringContextTests {

}
