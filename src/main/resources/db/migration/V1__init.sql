CREATE TABLE categories (
                            id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
                            name        VARCHAR(100)                            NOT NULL,
                            description TEXT                                    NOT NULL,
                            CONSTRAINT pk_categories PRIMARY KEY (id),
                            CONSTRAINT uc_categories_name UNIQUE (name)
);

CREATE TABLE roles (
                       id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
                       name    VARCHAR(50)                    NOT NULL,
                       CONSTRAINT pk_roles PRIMARY KEY (id),
                       CONSTRAINT uc_roles_name UNIQUE (name)
);

CREATE TABLE users (
                       id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
                       created_at TIMESTAMP WITHOUT TIME ZONE             NOT NULL,
                       updated_at TIMESTAMP WITHOUT TIME ZONE,
                       email      VARCHAR(255)                            NOT NULL,
                       password   VARCHAR(255)                            NOT NULL,
                       CONSTRAINT pk_users PRIMARY KEY (id),
                       CONSTRAINT uc_users_email UNIQUE (email)
);

CREATE TABLE courses (
                         id               BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
                         created_at       TIMESTAMP WITHOUT TIME ZONE             NOT NULL,
                         updated_at       TIMESTAMP WITHOUT TIME ZONE,
                         category_id      BIGINT                                  NOT NULL,
                         level            VARCHAR(255)                            NOT NULL,
                         name             VARCHAR(255)                            NOT NULL,
                         subtitle         VARCHAR(255),
                         image_url        VARCHAR(255)                            NOT NULL,
                         description      TEXT,
                         total_duration   INTEGER,
                         enrollment_count INTEGER,
                         rating           DECIMAL(3, 2),
                         status           VARCHAR(20)                            NOT NULL,
                         CONSTRAINT pk_courses PRIMARY KEY (id),
                         CONSTRAINT fk_courses_on_category FOREIGN KEY (category_id) REFERENCES categories (id),
                         CONSTRAINT chk_courses_status CHECK (status IN ('DRAFT', 'OPENED', 'COMING_SOON', 'ARCHIVED'))
);

CREATE TABLE course_objectives (
                                   course_id   BIGINT       NOT NULL,
                                   objective   VARCHAR(255) NOT NULL,
                                   order_index INTEGER      NOT NULL,
                                   CONSTRAINT pk_course_objectives PRIMARY KEY (course_id, order_index),
                                   CONSTRAINT fk_course_objectives_on_course FOREIGN KEY (course_id) REFERENCES courses (id)
);

CREATE TABLE lessons (
                         id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
                         course_id    BIGINT       NOT NULL,
                         order_number INTEGER      NOT NULL,
                         name         VARCHAR(255) NOT NULL,
                         description  TEXT,
                         content      TEXT,
                         media        JSONB,
                         CONSTRAINT pk_lessons PRIMARY KEY (id),
                         CONSTRAINT fk_lessons_on_course FOREIGN KEY (course_id) REFERENCES courses (id),
                         CONSTRAINT uc_lesson_order UNIQUE (course_id, order_number)
);

CREATE TABLE user_courses (
                              id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
                              user_id     BIGINT                                  NOT NULL,
                              course_id   BIGINT                                  NOT NULL,
                              enrolled_at TIMESTAMP WITHOUT TIME ZONE             NOT NULL,
                              status    VARCHAR(20)                                NOT NULL,
                              CONSTRAINT pk_user_courses PRIMARY KEY (id),
                              CONSTRAINT uc_user_course UNIQUE (user_id, course_id),
                              CONSTRAINT fk_user_courses_on_user   FOREIGN KEY (user_id) REFERENCES users (id),
                              CONSTRAINT fk_user_courses_on_course FOREIGN KEY (course_id) REFERENCES courses (id),
                              CONSTRAINT chk_user_courses_status CHECK (status IN ('ENROLLED', 'IN_PROGRESS', 'COMPLETED'))
);

CREATE TABLE user_roles (
                            role_id BIGINT NOT NULL,
                            user_id BIGINT NOT NULL,
                            CONSTRAINT pk_user_roles PRIMARY KEY (role_id, user_id),
                            CONSTRAINT fk_user_roles_on_role FOREIGN KEY (role_id) REFERENCES roles (id),
                            CONSTRAINT fk_user_roles_on_user FOREIGN KEY (user_id) REFERENCES users (id)
);
