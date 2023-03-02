create TYPE daysofweek AS ENUM (
    'MONDAY',
    'TUESDAY',
    'WEDNESDAY',
    'THURSDAY',
    'FRIDAY',
    'SATURDAY',
    'SUNDAY'
);

create TYPE Role AS ENUM (
    'ADMIN',
    'TEACHER',
    'STUDENT'
);

create type studystatus as enum ('active', 'notActive', 'break');

create table if not exists users (
   id serial not null primary key,
   username varchar(255) not null,
   password varchar(255) not null,
   roles varchar(50)
);

create table if not exists admins (
   id serial not null primary key,
   name VARCHAR(255) NOT NULL,
   surname varchar(255) not null,
   role varchar(50),
   user_id int,
   foreign key (user_id) references users(id) on delete cascade
);

create table if not exists teachers (
   id serial not null primary key,
   name VARCHAR(255) NOT NULL,
   surname varchar(255) not null,
   role varchar(50),
   user_id int,
   foreign key (user_id) references users(id) on delete cascade
);

create table if not exists groups (
   id serial not null primary key,
   subject_id int,
   day_of_week daysofweek,
   group_time time,
   teacher_id int,
   foreign key (subject_id) references subjects(id) on delete cascade,
   foreign key (teacher_id) references teachers(id) on delete cascade
);

create table students
(
    id             serial
        primary key,
    name           varchar(255) not null,
    surname        varchar(255) not null,
    role           varchar(50),
    robucks        integer,
    group_id       integer
        references groups
            on delete cascade,
    user_id        integer
        references users
            on delete cascade,
    age            integer,
    study_status   studystatus,
    place_of_study varchar(255),
    parent_name    varchar(255),
    phone_numbers  varchar(255)[]
);

create table lessons
(
    id          serial
        primary key,
    teacher_id  integer not null,
    topic       varchar(255),
    homework    varchar(255),
    lesson_date timestamp,
    group_id    integer
        constraint lessons_groups_id_fk
            references groups
);
create table if not exists subjects (
    id serial not null primary key,
    name varchar(255) not null
);

select * from lessons;

drop table students;