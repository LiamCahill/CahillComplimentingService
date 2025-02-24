package org.cahilll.css.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.cahilll.css.Model.Account;
import org.cahilll.css.Repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class DatabaseConnectionTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository; // Assuming you have a UserRepository

    @Container
    static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
    }

    @Test
    public void sampleTest() {
        // Use System.err for test output or configure logging
        System.err.println("testDatabaseConnection2"); 
        
        // Add more meaningful output
        String testMessage = "Running sample test...";
        System.err.println(testMessage);
        
        // Add actual test logic and assertions
        boolean condition = true;
        System.err.println("Test condition is: " + condition);
        assertTrue(condition, "Basic assertion should pass");
    }
    
    @Test
    public void testDatabaseConnection() {
        // Test that we can fetch a user we know exists in our test data
        Optional<Account> user = userRepository.findByUsername("TCAHILL");
        assertTrue(user.isPresent());
        assertEquals("7CAHILLS@ATT.NET", user.get().getEmail());
    }

    @Test
    public void testUserEndpoint() throws Exception {
        mockMvc.perform(get("/api/users/TCAHILL"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.email").value("7CAHILLS@ATT.NET"));
    }
}
