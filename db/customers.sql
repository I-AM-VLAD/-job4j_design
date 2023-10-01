CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);
INSERT INTO customers (first_name, last_name, age, country) VALUES ('Джек', 'Джонсон', 30, 'Канада');
INSERT INTO customers (first_name, last_name, age, country) VALUES ('Иван', 'Петров', 27, 'Финляндия');
INSERT INTO customers (first_name, last_name, age, country) VALUES ('Борис', 'Фёдоров', 24, 'США');

select * from customers where age = (select min(age) from customers);

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);
INSERT INTO orders (amount, customer_id) VALUES (0, 1);
INSERT INTO orders (amount, customer_id) VALUES (1, 2);
INSERT INTO orders (amount, customer_id) VALUES (2, 3);

select * from customers c
where c.id not in (select o.customer_id from orders o where amount > 0);