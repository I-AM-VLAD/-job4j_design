create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace function tax1()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax1_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax1();

create or replace function tax2()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = new.id;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax2_trigger
    before insert
    on products
    for each row
    execute procedure tax2();
insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);
create or replace function insert_history()
    returns trigger as
$$
    BEGIN
		insert into history_of_price (name, price, date)
		VALUES (new.name, new.price, current_date);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger history_trigger
    after insert
    on products
    for each row
    execute procedure insert_history();
insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
select * from history_of_price;

