/*
drop tables driver, logistic_center, role, operator,account,waybill,product,center_product;

drop database logisticcenterdb;*/

create database if not exists logisticcenterdb collate utf8_general_ci;

create table if not exists driver
(
    ID           int auto_increment
        primary key,
    FIO          varchar(70) null,
    Phone_number varchar(15) null
);

create table if not exists logistic_center
(
    ID       int auto_increment
        primary key,
    name     varchar(50)  null,
    Location varchar(150) null
);

create table if not exists role
(
    ID    int auto_increment
        primary key,
    title varchar(45) null
);

create table if not exists account
(
    ID      int         not null
        primary key,
    Login   varchar(25) null,
    Pass    varchar(25) null,
    Role_id int         null,
    constraint account_operator_ID_fk
        foreign key (Role_id) references role (ID)
);

create table if not exists operator
(
    ID           int auto_increment
        primary key,
    FIO          varchar(70) null,
    Phone_number varchar(15) null,
    Account_id int,
    constraint operator_account_id_fk
		foreign key (Account_id) references account (ID)
);

create table if not exists waybill
(
    ID               int auto_increment
        primary key,
    date_of_delivery varchar(50) null,
    date_of_shipment varchar(50) null,
    Driver_id        int         null,
    Operator_id      int         null,
    constraint waybill_driver_ID_fk
        foreign key (Driver_id) references driver (ID),
    constraint waybill_operator_ID_fk
        foreign key (Operator_id) references operator (ID)
);

create table if not exists product
(
    ID         int auto_increment
        primary key,
    Acticle    varchar(1000) null,
    Title      varchar(500)  null,
    Amount     int           null,
    waybill_id int           null,
    constraint product_waybill_ID_fk
        foreign key (waybill_id) references waybill (ID)
);

create table if not exists center_product
(
    ID         int auto_increment
        primary key,
    Center_id  int null,
    Product_id int null,
    constraint center_product_logistic_center_ID_fk
        foreign key (Center_id) references logistic_center (ID),
    constraint center_product_product_ID_fk
        foreign key (Product_id) references product (ID)
);

