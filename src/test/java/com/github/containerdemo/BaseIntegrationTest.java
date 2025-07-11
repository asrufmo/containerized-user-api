package com.github.containerdemo;

import com.testing.containers.managers.SpringBootContainerManager;
import com.testing.containers.providers.PostgresContainerProvider;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Map;

public abstract class BaseIntegrationTest {

    protected static PostgreSQLContainer<?> postgres;
    protected static SpringBootContainerManager appManager;
    protected static String baseUrl;

    @BeforeAll
    static void startContainers() {

        Network network = Network.newNetwork();

        PostgresContainerProvider postgresProvider = new PostgresContainerProvider()
                .withNetwork(network)
                .withAlias("postgres");

        PostgreSQLContainer<?> postgres = postgresProvider.getContainer();
        postgres.start();

        // Spring Boot App
        appManager = new SpringBootContainerManager()
                .withImage("containerized-user-api-dockerized-app:latest")
                .withNetwork(network)
                .withSpringProps(Map.of(
                        "datasource.url", "jdbc:postgresql://postgres:5432/testdb",
                        "datasource.username", "test",
                        "datasource.password", "test",
                        "jpa.hibernate.ddl-auto", "update",
                        "profiles.active", "test"
                ))
                .withHealthEndpoint("/api/health");

        appManager.start();
        baseUrl = appManager.getBaseUrl();
    }

    @AfterAll
    static void stopContainers() {
        if (appManager != null) appManager.stop();
        if (postgres != null && postgres.isRunning()) postgres.stop();
    }
}
