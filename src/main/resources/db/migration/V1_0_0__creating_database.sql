-- ROLES TABLE
CREATE TABLE roles (
    role_id SMALLINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(45) NOT NULL UNIQUE,
    create_date TIMESTAMP NULL,
    update_date TIMESTAMP NULL
);

-- USERS TABLE (NO role_id column now)
CREATE TABLE users (
    user_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    last_logon TIMESTAMP DEFAULT NULL,
    create_date TIMESTAMP DEFAULT NULL,
    update_date TIMESTAMP DEFAULT NULL
);

-- USERS_ROLES TABLE (JOIN TABLE)
CREATE TABLE users_roles (
    user_id BIGINT NOT NULL,
    role_id SMALLINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_users_roles_user
        FOREIGN KEY (user_id)
        REFERENCES users (user_id)
        ON DELETE CASCADE,
    CONSTRAINT fk_users_roles_role
        FOREIGN KEY (role_id)
        REFERENCES roles (role_id)
        ON DELETE CASCADE
);