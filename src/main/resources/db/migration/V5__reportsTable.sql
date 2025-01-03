CREATE TABLE if not exists reports
(
    id         int AUTO_INCREMENT PRIMARY KEY,
    file_name  VARCHAR(255),
    type       VARCHAR(255) NOT NULL,
    status     VARCHAR(255) NOT NULL,
    user_id    int          NOT NULL,
    created_at timestamp    not null default current_timestamp,
    updated_at timestamp    not null default current_timestamp on update current_timestamp,
    CONSTRAINT fk_reports_user_id FOREIGN KEY (user_id) REFERENCES users (id)
);

create index reports_user_id_idx
    on reports (user_id);