create table roles (
    id serial constraint role_pk primary key,
    role varchar(255) not null
);
create table users (
    id serial constraint user_pk primary key,
    username varchar(127),
    email varchar(255) not null unique,
    role_id integer not null constraint role_fk references roles(id)
);
create table rules (
    id serial constraint rule_pk primary key,
    rule varchar(255) not null
);
create table roles_rules (
    role_id integer constraint role_fk references roles(id),
    rule_id integer constraint rule_fk references rules(id) not null,
    constraint role_rule_pk primary key (role_id, rule_id)
);
create table categories (
    id serial constraint category_pk primary key,
    category varchar(255) not null
);
create table states (
    id serial constraint state_pk primary key,
    state varchar(255) not null
);
create table items (
    id serial constraint item_pk primary key,
    item_name varchar(255) not null,
    description text,
    created timestamp(0) not null,
    item_performer_id integer not null constraint user_fk references users(id),
    category_id integer not null constraint category_fk references categories(id),
    state_id integer not null constraint state_fk references states(id)
);
create table comments (
    id serial constraint comment_pk primary key,
    comment text not null,
    item_id integer not null constraint item_fk references items(id),
    comment_author_id integer not null constraint user_fk references users(id)
);
create table attachs (
    id serial constraint attach_pk primary key,
    file_name varchar(255) default 'nameless',
    file_type varchar(32) not null,
    file_size bigint not null,
    file_data bytea not null,
    item_id integer not null constraint item_fk references items(id)
);
