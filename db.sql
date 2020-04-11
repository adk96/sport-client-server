create database sport;
use sport;
create table store
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(255),
    description VARCHAR(255),
    quantity    INT,
    price       DOUBLE
);