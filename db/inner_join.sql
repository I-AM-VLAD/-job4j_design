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

select h.name, t.name
from telephone as t join human as h on t.human_id = h.id;

select h.name имя, t.name название
from telephone as t join human as h on t.human_id = h.id;

select h.name, t.name
from telephone as t join human as h on t.human_id = h.id
where h.name = 'Степан';

