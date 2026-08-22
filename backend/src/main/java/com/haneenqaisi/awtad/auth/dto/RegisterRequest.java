package com.haneenqaisi.awtad.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(

        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        @Size(max = 254, message = "Email must not exceed 254 characters")
        String email,

        @NotBlank(message = "Password is required")
        @Size(
                min = 8,
                max = 72,
                message = "Password must be between 8 and 72 characters"
        )
        String password,

        @NotBlank(message = "Display name is required")
        @Size(
                min = 2,
                max = 80,
                message = "Display name must be between 2 and 80 characters"
        )
        String displayName,

        @NotBlank(message = "Timezone is required")
        @Size(max = 64, message = "Timezone must not exceed 64 characters")
        String timezone

) {
}