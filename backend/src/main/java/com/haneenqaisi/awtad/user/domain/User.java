package com.haneenqaisi.awtad.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Locale;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 254)
    private String email;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "display_name", nullable = false, length = 80)
    private String displayName;

    @Column(nullable = false, length = 64)
    private String timezone;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_status", nullable = false, length = 20)
    private AccountStatus accountStatus;

    @Column(name = "last_login_at")
    private Instant lastLoginAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Version
    @Column(nullable = false)
    private long version;

    protected User() {
    }

    public User(
            String email,
            String passwordHash,
            String displayName,
            String timezone
    ) {
        this.email = normalizeEmail(email);
        this.passwordHash = requireText(passwordHash, "passwordHash");
        this.displayName = normalizeDisplayName(displayName);
        this.timezone = normalizeTimezone(timezone);
        this.accountStatus = AccountStatus.ACTIVE;
    }

    @PrePersist
    private void onCreate() {
        Instant now = Instant.now();
        createdAt = now;
        updatedAt = now;

        if (accountStatus == null) {
            accountStatus = AccountStatus.ACTIVE;
        }
    }

    @PreUpdate
    private void onUpdate() {
        updatedAt = Instant.now();
    }

    public void recordSuccessfulLogin() {
        lastLoginAt = Instant.now();
    }

    public void changePasswordHash(String passwordHash) {
        this.passwordHash = requireText(passwordHash, "passwordHash");
    }

    public void changeDisplayName(String displayName) {
        this.displayName = normalizeDisplayName(displayName);
    }

    public void suspend() {
        accountStatus = AccountStatus.SUSPENDED;
    }

    public void deactivate() {
        accountStatus = AccountStatus.DEACTIVATED;
    }

    public void activate() {
        accountStatus = AccountStatus.ACTIVE;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getTimezone() {
        return timezone;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public Instant getLastLoginAt() {
        return lastLoginAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public long getVersion() {
        return version;
    }

    private static String normalizeEmail(String email) {
        String normalized = requireText(email, "email")
                .trim()
                .toLowerCase(Locale.ROOT);

        if (normalized.length() > 254) {
            throw new IllegalArgumentException("email must not exceed 254 characters");
        }

        return normalized;
    }

    private static String normalizeDisplayName(String displayName) {
        String normalized = requireText(displayName, "displayName").trim();

        if (normalized.length() < 2 || normalized.length() > 80) {
            throw new IllegalArgumentException(
                    "displayName must contain between 2 and 80 characters"
            );
        }

        return normalized;
    }

    private static String normalizeTimezone(String timezone) {
        return ZoneId.of(requireText(timezone, "timezone").trim()).getId();
    }

    private static String requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }

        return value;
    }
}