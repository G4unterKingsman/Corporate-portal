--liquibase formatted sql


-- changeset gaunter:1
create table public.employees (
       age integer,
       joined date,
       work_experience_years integer,
       id bigserial not null,
       description varchar(255),
       name varchar(255),
       primary key (id)
);



-- changeset gaunter:2
create table public.reports (
       created timestamp(6),
       id bigserial not null,
       linked_task_id bigint,
       report_employ_id bigint,
       updated timestamp(6),
       description varchar(255),
       title varchar(255),
       primary key (id)
);


-- changeset gaunter:3
create table public.tasks (
       time_allowed integer,
       time_cancelled integer,
       completed timestamp(6),
       created timestamp(6),
       deadline timestamp(6),
       id bigserial not null,
       updated timestamp(6),
       who_gave_task_id bigint,
       who_given_task_id bigint,
       description varchar(255),
       status varchar(255) check (status in ('CREATED','IN_PROGRESS','COMPLETED','CANCELLED')),
       title varchar(255),
       primary key (id)
);


-- changeset gaunter:4
create table public.user_account_roles (
       user_account_id bigint not null,
       roles varchar(255) check (roles in ('ROLE_USER','ROLE_ADMIN'))
);


-- changeset gaunter:5
create table public.users (
        active boolean not null,
        employee_id bigint unique,
        id bigserial not null,
        password varchar(255) not null,
        username varchar(255) not null unique,
        primary key (id)
);

