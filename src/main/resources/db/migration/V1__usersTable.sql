CREATE TABLE `users`
(
    `id`         int          NOT NULL AUTO_INCREMENT,
    `email`      varchar(150) NOT NULL,
    `password`   varchar(255) NOT NULL,
    `role`       varchar(50)  NOT NULL DEFAULT 'USER',
    `created_at` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_active`  tinyint      NOT NULL DEFAULT '1',
    `nickname`   varchar(25)  NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `email_UNIQUE` (`email`),
    UNIQUE KEY `nickname_UNIQUE` (`nickname`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci

