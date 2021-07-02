create table global_settings (
id integer not null auto_increment,
code varchar(255) not null,
name varchar(255) not null,
value varchar(255) not null,
primary key (id)) ENGINE=InnoDB AUTO_INCREMENT=4;

insert into global_settings values(1, 'MULTIUSER_MODE', 'Многопользовательский режим', 'NO');
insert into global_settings values(2, 'POST_PREMODERATION', 'Премодерация постов', 'YES');
insert into global_settings values(3, 'STATISTICS_IS_PUBLIC', 'Показывать всем статистику блога', 'YES');
