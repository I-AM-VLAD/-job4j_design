create table bears(
	id serial primary key,
	name varchar(255),
	weight int,
	habitat text
);
select * from bears;
insert into bears(name, weight, habitat) values ('Белый медведь', 300 ,'Северный полюс');
update bears set name = 'Гризли';
delete from bears;