create table if not exists ideas
(
    id              int auto_increment
        primary key,
    name            varchar(255)                         not null,
    description     text                                 not null,
    goal_amount     decimal(12, 2) default 0.00          not null,
    current_amount  decimal(12, 2) default 0.00          not null,
    status          varchar(45)    default 'IN_PROGRESS' not null,
    created_at      timestamp                            NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp                            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    donations_count int            default 0             not null,
    image_url       varchar(255)                         null,
    user_id         int                                  not null,
    category        varchar(100)                         not null,
    constraint fk_ideas_user_id
        foreign key (user_id) references users (id)
);

create index ideas_user_id_idx
    on ideas (user_id);