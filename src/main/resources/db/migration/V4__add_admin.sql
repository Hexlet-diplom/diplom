INSERT INTO users (created_at, updated_at, email, password) VALUES
                                                                (now(), now(), 'admin@example.com', '$2a$12$LbThgBHQq9DlajD1YJyDhesIABYjKnnwaIjEy3xIDBc2BSM5GmubS');
INSERT INTO user_roles (role_id, user_id) VALUES (1, 1);