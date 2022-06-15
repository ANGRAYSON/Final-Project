create database if not exists hvac_parts;
use hvac_parts;

drop table if exists parts_out;
drop table if exists inventory;
drop table if exists technicians;
drop table if exists parts;
drop table if exists locations;

create table technicians(
  employee_num int NOT NULL AUTO_INCREMENT,
  first_name varchar(20) NOT NULL,
  last_name varchar(20) NOT NULL,
  active_status enum('ACTIVE', 'INACTIVE'),
  PRIMARY KEY(employee_num)
);

create table parts(
  part_num varchar(20) NOT NULL,
  part_name varchar(30) NOT NULL,
  type enum('HEATING', 'COOLING','ELECTRIC') NOT NULL,
  PRIMARY KEY(part_num)
);

create table locations(
  location_num int NOT NULL AUTO_INCREMENT,
  location_name varchar(30) NOT NULL,
  PRIMARY KEY(location_num)
);

create table inventory(
  part_num_fk varchar(20) NOT NULL,
  location_num_fk int NOT NULL,
  stock int NOT NULL,
  FOREIGN KEY(part_num_fk) references parts(part_num),
  FOREIGN KEY(location_num_fk) references locations(location_num)
);

create table parts_out(
  order_num int NOT NULL AUTO_INCREMENT,
  employee_num_fk int NOT NULL,
  part_num_fk varchar(20) NOT NULL,
  location_num_fk int NOT NULL,
  amount_out int NOT NULL,
  PRIMARY KEY(order_num),
  FOREIGN KEY(employee_num_fk) references technicians(employee_num),
  FOREIGN KEY(part_num_fk) references parts(part_num),
  FOREIGN KEY(location_num_fk) references locations(location_num)
);

