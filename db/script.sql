create table human(
    id serial primary key,
    name varchar(255)
);
create table telephone(
    id serial primary key,
    name varchar(255),
    human_id int references human(id)
);
insert into human(name) values ('Олег');
insert into telephone(name, human_id) values ('samsung', 1);
select * from telephone;

create table students(
     id serial primary key,
     name varchar(255)
 );
create table books(
     id serial primary key,
     name varchar(255)
 );
create table students_books(
     id serial primary key,
     student_id int references students(id),
     book_id int references books(id)
 );
insert into students(name) values ('Ivan');
insert into students(name) values ('Kirill');

insert into books(name) values ('Робинзон Крузо');
insert into books(name) values ('Путешествие к центру Земли');

insert into students_books(student_id, book_id) values (1, 1);
insert into students_books(student_id, book_id) values (1, 2);
insert into students_books(student_id, book_id) values (2, 1);
insert into students_books(student_id, book_id) values (2, 2);

create table identification_number(
    id serial primary key,
    name varchar(255)
);
create table people(
    id serial primary key,
    name varchar(255),
    identification_number_id int references identification_number(id) unique
);

