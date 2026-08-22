package com.haneenqaisi.awtad.auth.controller;

import com.haneenqaisi.awtad.user.domain.User;
import com.haneenqaisi.awtad.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void cleanDatabase() {
        userRepository.deleteAll();
        userRepository.flush();
    }

    @Test
    void shouldRegisterUserAndHashPassword() throws Exception {
        String requestBody = """
                {
                  "email": "registration.test@example.com",
                  "password": "StrongPass123!",
                  "displayName": "Haneen",
                  "timezone": "Asia/Hebron"
                }
                """;

        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.email")
                        .value("registration.test@example.com"))
                .andExpect(jsonPath("$.displayName").value("Haneen"))
                .andExpect(jsonPath("$.timezone").value("Asia/Hebron"))
                .andExpect(jsonPath("$.accountStatus").value("ACTIVE"))
                .andExpect(jsonPath("$.password").doesNotExist())
                .andExpect(jsonPath("$.passwordHash").doesNotExist());

        User savedUser = userRepository
                .findByEmail("registration.test@example.com")
                .orElseThrow();

        assertNotEquals("StrongPass123!", savedUser.getPasswordHash());
        assertTrue(passwordEncoder.matches(
                "StrongPass123!",
                savedUser.getPasswordHash()
        ));
    }

    @Test
    void shouldRejectDuplicateEmail() throws Exception {
        String requestBody = """
                {
                  "email": "duplicate.test@example.com",
                  "password": "StrongPass123!",
                  "displayName": "Haneen",
                  "timezone": "Asia/Hebron"
                }
                """;

        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status").value(409))
                .andExpect(jsonPath("$.message")
                        .value("An account with this email already exists"));
    }

    @Test
    void shouldRejectInvalidRegistrationRequest() throws Exception {
        String requestBody = """
                {
                  "email": "invalid-email",
                  "password": "short",
                  "displayName": "H",
                  "timezone": ""
                }
                """;

        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.fieldErrors.email").exists())
                .andExpect(jsonPath("$.fieldErrors.password").exists())
                .andExpect(jsonPath("$.fieldErrors.displayName").exists())
                .andExpect(jsonPath("$.fieldErrors.timezone").exists());
    }
}