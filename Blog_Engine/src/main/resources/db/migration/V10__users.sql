create table users (
id integer not null auto_increment,
is_moderator tinyint not null,
reg_time datetime not null,
name varchar(255) not null,
email varchar(255) not null,
password varchar(255) not null,
code varchar(255),
photo varchar(9999),
primary key (id)) ENGINE=InnoDB AUTO_INCREMENT=5;

insert into users values(1, 1, '2018-11-22 20:12:00', 'Mammoth', '123456@gmail.com', '123456', null, null);
insert into users values(2, 1, '2018-11-23 20:12:00', 'Omsk55', '987654@gmail.com', '987654', null, null);
insert into users values(3, 0, '2019-11-22 20:12:00', 'Morpheus', '000000@gmail.com', '000000', null, null);
insert into users values(4, 0, '2020-02-27 20:12:00', 'Trinity', '555555@gmail.com', '555555', null, null);
