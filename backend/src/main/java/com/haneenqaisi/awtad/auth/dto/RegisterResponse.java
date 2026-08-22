package com.haneenqaisi.awtad.auth.dto;

import com.haneenqaisi.awtad.user.domain.AccountStatus;
import com.haneenqaisi.awtad.user.domain.User;

import java.time.Instant;
import java.util.UUID;

public record RegisterResponse(
        UUID id,
        String email,
        String displayName,
        String timezone,
        AccountStatus accountStatus,
        Instant createdAt
) {

    public static RegisterResponse from(User user) {
        return new RegisterResponse(
                user.getId(),
                user.getEmail(),
                user.getDisplayName(),
                user.getTimezone(),
                user.getAccountStatus(),
                user.getCreatedAt()
        );
    }
}