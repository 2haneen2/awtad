package com.haneenqaisi.awtad.user.repository;

import com.haneenqaisi.awtad.user.domain.AccountStatus;
import com.haneenqaisi.awtad.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
     void shouldSaveAndFindUserByNormalizedEmail() {

        User user = new User(
                "  HANEEN.TEST@EXAMPLE.COM  ",
                "hashed-password",
                "Haneen",
                "Asia/Hebron"
        );

        userRepository.saveAndFlush(user);

        Optional<User> result =
                userRepository.findByEmail("haneen.test@example.com");

        assertTrue(result.isPresent());

        User savedUser = result.get();

        assertNotNull(savedUser.getId());
        assertNotNull(savedUser.getCreatedAt());
        assertNotNull(savedUser.getUpdatedAt());
        assertEquals("haneen.test@example.com", savedUser.getEmail());
        assertEquals("Haneen", savedUser.getDisplayName());
        assertEquals("Asia/Hebron", savedUser.getTimezone());
        assertEquals(AccountStatus.ACTIVE, savedUser.getAccountStatus());
    }
}