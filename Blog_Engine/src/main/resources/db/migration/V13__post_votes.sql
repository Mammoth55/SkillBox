create table post_votes (
id integer not null auto_increment,
user_id integer not null,
post_id integer not null,
time datetime not null,
value tinyint not null,
primary key (id)) ENGINE=InnoDB AUTO_INCREMENT=7;

alter table post_votes add constraint FK9jh5u17tmu1g7xnlxa77ilo3u foreign key (post_id) references posts (id);
alter table post_votes add constraint FK9q09ho9p8fmo6rcysnci8rocc foreign key (user_id) references users (id);

insert into post_votes values(1, 1, 1, '2021-05-15 03:22:33', 1);
insert into post_votes values(2, 2, 1, '2021-05-15 01:11:29', 1);
insert into post_votes values(3, 3, 3, '2021-05-15 02:31:00', 1);
insert into post_votes values(4, 4, 4, '2021-05-15 02:38:00', -1);
insert into post_votes values(5, 1, 5, '2021-05-15 02:31:00', 1);
insert into post_votes values(6, 4, 5, '2021-05-15 02:31:00', -1);
