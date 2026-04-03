-- drops
DROP TABLE IF EXISTS public.daily_logs;

DROP SEQUENCE IF EXISTS public.logs_id;

DROP TABLE IF EXISTS public.customers;

DROP SEQUENCE IF EXISTS public.customers_id;

-- creates

CREATE SEQUENCE public.customers_id
    INCREMENT 1
    START WITH 1;

CREATE TABLE IF NOT EXISTS public.customers
(
    id integer NOT NULL DEFAULT nextval('customers_id'),
    name varchar NOT NULL,
    CONSTRAINT customers_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT EXISTS public.logs_id
    INCREMENT 1
    START WITH 1;


CREATE TABLE IF NOT EXISTS public.daily_logs
(
    id integer NOT NULL DEFAULT nextval('logs_id'),
    date date NOT NULL DEFAULT CURRENT_DATE,
    customer_id integer NOT NULL,
    CONSTRAINT daily_logs_pkey PRIMARY KEY (id),
    CONSTRAINT daily_logs_customers_fkey
          FOREIGN KEY(customer_id)
    	  REFERENCES public.customers(id)
    	  ON DELETE CASCADE
);

insert into customers(name) values ('Ivanov');
insert into customers(name) values ('Petrov');
insert into customers(name) values ('Sidorov');

