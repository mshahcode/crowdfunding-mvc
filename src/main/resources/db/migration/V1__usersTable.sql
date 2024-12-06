CREATE TABLE IF NOT EXISTS `users`
(
    `id`         int                                     NOT NULL AUTO_INCREMENT,
    `email`      varchar(50) COLLATE utf8mb3_unicode_ci  NOT NULL,
    `password`   varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
    `role`       varchar(50) COLLATE utf8mb3_unicode_ci  NOT NULL DEFAULT 'USER',
    `created_at` timestamp                               NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp                               NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_active`  tinyint                                 NOT NULL DEFAULT '1',
    `nickname`   varchar(50) COLLATE utf8mb3_unicode_ci  NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci