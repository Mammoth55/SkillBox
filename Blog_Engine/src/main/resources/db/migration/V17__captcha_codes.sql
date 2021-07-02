create table captcha_codes (
id integer not null auto_increment,
time datetime not null,
code varchar(255) not null,
secret_code varchar(255) not null,
primary key (id)) ENGINE=InnoDB AUTO_INCREMENT=4;

insert into captcha_codes values(1, '2021-06-05 23:21:00', 'выхухоль55', '1111111111');
insert into captcha_codes values(2, '2021-06-06 08:22:00', 'шиншилла99', '2222222222');
insert into captcha_codes values(3, '2021-06-07 17:41:00', 'интуиция00', '-1000000000');
