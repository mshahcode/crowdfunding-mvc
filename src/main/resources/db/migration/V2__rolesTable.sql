CREATE TABLE IF NOT EXISTS roles
(
    `id`      INT          NOT NULL AUTO_INCREMENT,
    `name`    VARCHAR(100) NOT NULL,
    `user_id` INT          NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_users_roles_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_users_roles`
        FOREIGN KEY (`user_id`)
            REFERENCES users (`id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
);
