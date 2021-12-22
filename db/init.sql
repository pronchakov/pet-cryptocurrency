create table blocks
(
    id            varchar not null
        constraint blocks_pk
            primary key,
    previous_hash varchar not null,
    data          varchar not null,
    create_date   bigint  not null,
    nonce         bigint  not null,
    hash          varchar not null
);

alter table blocks
    owner to postgres;

create index blocks_create_date_index
    on blocks (create_date desc);

create index blocks_hash_index
    on blocks (hash);

create index blocks_previous_hash_index
    on blocks (previous_hash);

