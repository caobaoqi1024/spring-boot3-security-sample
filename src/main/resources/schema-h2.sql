CREATE TABLE `user`
(
    id          BIGINT    NOT NULL UNIQUE AUTO_INCREMENT,
    username    VARCHAR   NOT NULL UNIQUE,
    email       VARCHAR   NOT NULL UNIQUE,
    password    VARCHAR   NOT NULL,
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    enable      BOOLEAN   NOT NULL DEFAULT true,
    PRIMARY KEY (id)
);

CREATE TABLE `permission`
(
    id   BIGINT  NOT NULL UNIQUE AUTO_INCREMENT,
    code VARCHAR NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE `role`
(
    id   BIGINT  NOT NULL UNIQUE AUTO_INCREMENT,
    code VARCHAR NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE `role_permission_map`
(
    id            BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
    role_id       BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE `user_role_map`
(
    id      BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE `user_role_map`
    ADD FOREIGN KEY (user_id) REFERENCES `user` (id)
        ON UPDATE NO ACTION ON DELETE CASCADE;
ALTER TABLE `user_role_map`
    ADD FOREIGN KEY (role_id) REFERENCES `role` (id)
        ON UPDATE NO ACTION ON DELETE CASCADE;
ALTER TABLE `role_permission_map`
    ADD FOREIGN KEY (role_id) REFERENCES `role` (id)
        ON UPDATE NO ACTION ON DELETE CASCADE;
ALTER TABLE `role_permission_map`
    ADD FOREIGN KEY (permission_id) REFERENCES `permission` (id)
        ON UPDATE NO ACTION ON DELETE CASCADE;
