select f_insert_data('product_1', 'producer_1', 25, 50);
select f_insert_data('product_2', 'producer_2', 15, 32);
select f_insert_data('product_3', 'producer_3', 8, 115);

create or replace procedure delete_data(d_id integer)
language 'plpgsql'
as $$
    BEGIN
    delete from products where id = d_id;
    END
$$;
call delete_data(1);
select * from products;

create or replace function f_delete_data(d_id integer)
returns integer
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
        delete from products where id = d_id;
        result = 0;
        return result;
    end;
$$;
select f_delete_data(2);