package com.haneenqaisi.awtad.auth.service;

import com.haneenqaisi.awtad.auth.dto.RegisterRequest;
import com.haneenqaisi.awtad.auth.dto.RegisterResponse;
import com.haneenqaisi.awtad.auth.exception.EmailAlreadyRegisteredException;
import com.haneenqaisi.awtad.user.domain.User;
import com.haneenqaisi.awtad.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public RegisterResponse register(RegisterRequest request) {
        String normalizedEmail = normalizeEmail(request.email());

        if (userRepository.existsByEmail(normalizedEmail)) {
            throw new EmailAlreadyRegisteredException();
        }

        String passwordHash = passwordEncoder.encode(request.password());

        User user = new User(
                normalizedEmail,
                passwordHash,
                request.displayName(),
                request.timezone()
        );

        User savedUser = userRepository.saveAndFlush(user);

        return RegisterResponse.from(savedUser);
    }

    private String normalizeEmail(String email) {
        return email.trim().toLowerCase(Locale.ROOT);
    }
}