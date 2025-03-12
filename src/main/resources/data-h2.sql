INSERT INTO `user` (username, email, password)
VALUES ('admin', 'admin1024@gmail.com', '$2a$10$RVVtn/6vJHotWZRcJIjMNec5CN6uHYtVsCkP.9DwdpfGclxVO7jAu'),
       ('general', 'general1024@gmail.com', '$2a$10$RVVtn/6vJHotWZRcJIjMNec5CN6uHYtVsCkP.9DwdpfGclxVO7jAu');

INSERT INTO `role` (code)
VALUES ('ADMIN'),
       ('GENERAL');

INSERT INTO `permission` (code)
VALUES ('book:read'),
       ('book:write');

INSERT INTO `user_role_map` (user_id, role_id)
VALUES (1, 1),
       (2, 2);

INSERT INTO `role_permission_map` (role_id, permission_id)
VALUES (1, 1),
       (1, 2),
       (2, 1);
