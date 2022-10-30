DROP SCHEMA IF EXISTS slaughterhouse CASCADE;
CREATE SCHEMA IF NOT EXISTS slaughterhouse;

SET SCHEMA 'slaughterhouse';

CREATE DOMAIN product_type VARCHAR(50)
    CHECK (VALUE IN ('halfAnimal', 'sameTypeParts'));

CREATE TABLE animal
(
    reg_num     serial primary key,
    animal_type varchar(50),
    date_time   timestamp,
    farm        varchar(100),
    weight      double precision
);

CREATE TABLE part(
    part_id serial primary key,
    og_animal_reg_num int references animal(reg_num),
    part_type varchar(50),
    weight double precision
);

CREATE TABLE product(
    product_id serial primary key,
    product_type varchar(50)
);

CREATE TABLE tray(
    tray_id serial primary key,
    max_weight double precision,
    animal_type varchar(50),
    part_type varchar(50)
);

CREATE TABLE part_in_tray(
    parts_part_id int references part(part_id),
    tray_tray_id int references tray(tray_id),
    PRIMARY KEY (parts_part_id, tray_tray_id)
);

CREATE TABLE part_in_product(
    parts_part_id int references part(part_id),
    product_product_id int references product(product_id),
    PRIMARY KEY (parts_part_id, product_product_id)
);

CREATE TABLE tray_in_product(
    trays_tray_id int references tray(tray_id),
    product_product_id int references product(product_id),
    PRIMARY KEY (trays_tray_id, product_product_id)
);