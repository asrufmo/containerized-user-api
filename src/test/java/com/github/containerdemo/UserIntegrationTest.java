
package com.github.containerdemo;

import com.testing.containers.core.ContainerOrchestrator;
import com.testing.containers.managers.DatabaseContainerManager;
import com.testing.containers.providers.PostgresContainerProvider;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserIntegrationTest {

    static PostgresContainerProvider postgresProvider = new PostgresContainerProvider();

    static {
        ContainerOrchestrator orchestrator = new ContainerOrchestrator();
        orchestrator.register(new DatabaseContainerManager(List.of(postgresProvider)));
        orchestrator.startAll();
    }

    @DynamicPropertySource
    static void overrideProps(DynamicPropertyRegistry registry) {
        PostgreSQLContainer<?> container = postgresProvider.getContainer();
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testUserCreationAndRetrieval() {
        String baseUrl = "http://localhost:" + port + "/users";
        restTemplate.postForEntity(baseUrl, Map.of("name", "Alice"), String.class);
        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl, String.class);
        assertThat(response.getBody()).contains("Alice");
    }
}
