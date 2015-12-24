DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;

ALTER SEQUENCE global_seq RESTART WITH 100000;


INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);


INSERT INTO meals (id, datetime, description, calories, user_id)
VALUES (1, '2015-11-30 14:00:00', 'обед', 1000, 100000);
INSERT INTO meals (id, datetime, description, calories, user_id)
VALUES (2, '2015-11-30 18:00:00', 'ужин', 500, 100000);

-- INSERT INTO meals (id, datetime, description, calories, user_id)
-- VALUES (3, '2015-11-30 14:00:00', 'обед Админ', 1100, 100001);
-- INSERT INTO meals (id, datetime, description, calories, user_id)
-- VALUES (4, '2015-11-30 18:00:00', 'ужин Админ', 600, 100001);