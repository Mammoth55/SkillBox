create table tag2post (
id integer not null auto_increment,
post_id integer not null,
tag_id integer not null,
primary key (id)) ENGINE=InnoDB AUTO_INCREMENT=9;

alter table tag2post add constraint FKpjoedhh4h917xf25el3odq20i foreign key (post_id) references posts (id);
alter table tag2post add constraint FKjou6suf2w810t2u3l96uasw3r foreign key (tag_id) references tags (id);

insert into tag2post values(1, 1, 1);
insert into tag2post values(2, 3, 2);
insert into tag2post values(3, 3, 3);
insert into tag2post values(4, 4, 4);
insert into tag2post values(5, 2, 1);
insert into tag2post values(6, 5, 5);
insert into tag2post values(7, 1, 2);
insert into tag2post values(8, 3, 1);
