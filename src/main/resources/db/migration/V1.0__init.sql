CREATE TABLE orders
(
    id            uuid        NOT NULL
        PRIMARY KEY,
    order_name    varchar     NOT NULL,
    order_phone   varchar(16) NOT NULL,
    order_fast    boolean     NOT NULL,
    order_address varchar,
    order_price numeric
);

CREATE TABLE products
(
    ingredient varchar NOT NULL PRIMARY KEY UNIQUE,
    price numeric
);


CREATE TABLE order_products
(
    ingredient varchar NOT NULL REFERENCES products (ingredient),
    order_id      uuid   NOT NULL REFERENCES orders (id),
    count_ingredient INTEGER NOT NULL
);

INSERT INTO burger.products (ingredient, price) VALUES ('bacon', 0.75);
INSERT INTO burger.products (ingredient, price) VALUES ('cheese', 0.95);
INSERT INTO burger.products (ingredient, price) VALUES ('pickle', 0.55);
INSERT INTO burger.products (ingredient, price) VALUES ('chicken', 1);
INSERT INTO burger.products (ingredient, price) VALUES ('meat', 1.2);
INSERT INTO burger.products (ingredient, price) VALUES ('salad', 0.45);