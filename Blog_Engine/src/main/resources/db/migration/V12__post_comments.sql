create table post_comments (
id integer not null auto_increment,
parent_id integer,
post_id integer not null,
user_id integer not null,
time datetime not null,
text varchar(9999) not null,
primary key (id)) ENGINE=InnoDB AUTO_INCREMENT=8;

alter table post_comments add constraint FKaawaqxjs3br8dw5v90w7uu514 foreign key (post_id) references posts (id);
alter table post_comments add constraint FKsnxoecngu89u3fh4wdrgf0f2g foreign key (user_id) references users (id);

insert into post_comments values(1, null, 5, 2, '2021-05-14 11:33:00', 'Its true )))');
insert into post_comments values(2, null, 2, 2, '2021-05-14 14:44:22', 'May be NOT... )');
insert into post_comments values(3, null, 3, 3, '2021-05-14 14:55:00', 'EXACTLY !!!');
insert into post_comments values(4, null, 4, 4, '2021-05-14 19:12:11', 'I am still HERE... )))');
insert into post_comments values(5, null, 4, 3, '2021-05-15 09:12:00', 'I am almost searching for work ! )))');
insert into post_comments values(6, null, 3, 4, '2021-05-15 11:55:11', 'May be YES... )');
insert into post_comments values(7, null, 3, 1, '2021-05-15 13:55:00', 'Its impossible !!!');
