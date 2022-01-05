package com.lukas8219.pollbe.helper;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
public abstract class DatabaseIT {

    @Container
    protected static MySQLContainer database = new MySQLContainer("mysql:latest")
            .withDatabaseName("poll")
            .withPassword("root")
            .withUsername("root");

    //TODO add reuse when necessary

    @DynamicPropertySource
    protected static void steDatasourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", database::getJdbcUrl);
        registry.add("spring.datasource.password", database::getPassword);
        registry.add("spring.datasource.username", database::getUsername);
        registry.add("spring.datasource.driver-class-name", database::getDriverClassName);
    }
}
