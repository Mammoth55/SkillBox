create table tags (
id integer not null auto_increment,
name varchar(255) not null,
primary key (id)) ENGINE=InnoDB AUTO_INCREMENT=6;

insert into tags values(1, 'JAVA');
insert into tags values(2, 'SPRING');
insert into tags values(3, 'HIBERNATE');
insert into tags values(4, 'SKILLBOX');
insert into tags values(5, 'HEADHUNTER');
