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

create table if not exists students (
   id serial not null primary key,
   name VARCHAR(255) NOT NULL,
   surname varchar(255) not null,
   role varchar(50),
   robucks int,
   group_id int,
   foreign key (group_id) references groups(id) on delete cascade,
   user_id int,
   foreign key (user_id) references users(id) on delete cascade
);

create table if not exists subjects (
    id serial not null primary key,
    name varchar(255) not null
);
