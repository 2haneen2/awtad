CREATE TABLE users
(
    id             UUID         PRIMARY KEY DEFAULT gen_random_uuid(),
    email          VARCHAR(254) NOT NULL,
    password_hash  VARCHAR(255) NOT NULL,
    display_name   VARCHAR(80)  NOT NULL,
    timezone       VARCHAR(64)  NOT NULL DEFAULT 'UTC',
    account_status VARCHAR(20)  NOT NULL DEFAULT 'ACTIVE',
    last_login_at  TIMESTAMPTZ,
    created_at     TIMESTAMPTZ  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMPTZ  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    version        BIGINT       NOT NULL DEFAULT 0,

    CONSTRAINT ck_users_email_normalized
        CHECK (email = LOWER(BTRIM(email))),

    CONSTRAINT ck_users_email_not_blank
        CHECK (CHAR_LENGTH(BTRIM(email)) >= 3),

    CONSTRAINT ck_users_display_name_length
        CHECK (CHAR_LENGTH(BTRIM(display_name)) BETWEEN 2 AND 80),

    CONSTRAINT ck_users_account_status
        CHECK (account_status IN ('ACTIVE', 'SUSPENDED', 'DEACTIVATED'))
);

CREATE UNIQUE INDEX uq_users_email_lower
    ON users (LOWER(email));


CREATE TABLE refresh_tokens
(
    id          UUID        PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id     UUID        NOT NULL,
    token_hash  VARCHAR(64) NOT NULL,
    expires_at  TIMESTAMPTZ NOT NULL,
    revoked_at  TIMESTAMPTZ,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_refresh_tokens_user
        FOREIGN KEY (user_id)
            REFERENCES users (id)
            ON DELETE CASCADE,

    CONSTRAINT uq_refresh_tokens_token_hash
        UNIQUE (token_hash),

    CONSTRAINT ck_refresh_tokens_expiry
        CHECK (expires_at > created_at),

    CONSTRAINT ck_refresh_tokens_revocation
        CHECK (revoked_at IS NULL OR revoked_at >= created_at)
);

CREATE INDEX idx_refresh_tokens_user_id
    ON refresh_tokens (user_id);

CREATE INDEX idx_refresh_tokens_active
    ON refresh_tokens (user_id, expires_at)
    WHERE revoked_at IS NULL;