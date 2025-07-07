CREATE TABLE roles (
    role_id SMALLINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(45) NOT NULL,
    create_date TIMESTAMP NULL,
    update_date TIMESTAMP NULL,
    UNIQUE (role_id),
    UNIQUE (name)
);

CREATE TABLE users (
    user_id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    role_id SMALLINT NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    last_logon TIMESTAMP DEFAULT NULL,
    create_date TIMESTAMP DEFAULT NULL,
    update_date TIMESTAMP DEFAULT NULL,
    UNIQUE (user_id),
    UNIQUE (username),
    UNIQUE (email),
    CONSTRAINT users_roles_fk FOREIGN KEY (role_id)
        REFERENCES roles (role_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);