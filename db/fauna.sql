create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);
insert into fauna (name, avg_age, discovery_date)
values ('swordfish', 9000, '1800-05-02');
insert into fauna (name, avg_age, discovery_date)
values ('hare', 11000, '1600-05-02');
insert into fauna (name, avg_age, discovery_date)
values ('wolf', 17000, '1970-05-02');

select * from fauna where name like '%fish%';
select * from fauna where avg_age >= 10000 and avg_age <= 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';


