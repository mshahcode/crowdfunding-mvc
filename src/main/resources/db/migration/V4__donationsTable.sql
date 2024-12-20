create table if not exists donations
(
    id            int auto_increment primary key,
    user_id       int            not null,
    idea_id       int            not null,
    amount        decimal(12, 2) not null,
    card_type     varchar(20)    not null,
    card_number   TEXT           not null,
    card_cvv      TEXT           not null,
    card_exp_date varchar(7)     not null,
    message       TEXT           null,
    created_at    timestamp      not null default current_timestamp,
    updated_at    timestamp      not null default current_timestamp on update current_timestamp,
    constraint fk_donations_idea_id
        foreign key (idea_id) references ideas (id),
    constraint fk_donations_user_id
        foreign key (user_id) references users (id)
);


create index donations_idea_id_idx
    on donations (idea_id);

create index donations_user_id_idx
    on donations (user_id);
