DROP TABLE IF EXISTS item;

create table item (
id integer not null,
carton_price integer not null,
carton_size integer not null,
name varchar(255),
primary key (id));

INSERT INTO item (id,name,carton_size, carton_price) VALUES
  (1,'penguinEar',20, 175),
  (2,'horseShoe',5, 825);



