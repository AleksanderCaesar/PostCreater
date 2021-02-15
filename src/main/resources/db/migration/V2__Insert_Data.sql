INSERT INTO users ( user_id, name, password, email, is_moderator)
VALUES (1, 'Alex', '123', 'mail@mail.ru', 1);

INSERT INTO posts (is_active, moderator_id, text, title, user_id)
VALUES (1, 1, 'Some text', 'Coder life', 1);
