CREATE TABLE IF NOT EXISTS users (
    id    BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    name  VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS slots (
    id         BIGSERIAL PRIMARY KEY,
    user_id    BIGINT      NOT NULL REFERENCES users (id),
    start_time TIMESTAMP   NOT NULL,
    end_time   TIMESTAMP   NOT NULL,
    status     VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS meetings (
    id          BIGSERIAL PRIMARY KEY,
    slot_id     BIGINT       NOT NULL UNIQUE REFERENCES slots (id),
    title       VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS meeting_participants (
    id         BIGSERIAL PRIMARY KEY,
    meeting_id BIGINT      NOT NULL REFERENCES meetings (id),
    user_id    BIGINT      NOT NULL REFERENCES users (id),
    status     VARCHAR(50) NOT NULL,
    UNIQUE (meeting_id, user_id)
);
