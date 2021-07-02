create table posts (
id integer not null auto_increment,
is_active tinyint not null,
moderation_status enum('NEW', 'ACCEPTED', 'DECLINED') not null,
moderator_id integer,
text varchar(9999) not null,
time datetime not null,
title varchar(255) not null,
view_count integer not null,
user_id integer not null,
primary key (id)) ENGINE=InnoDB AUTO_INCREMENT=6;

alter table posts add constraint FK5lidm6cqbc7u4xhqpxm898qme foreign key (user_id) references users (id);

insert into posts values(1, 1, 'ACCEPTED', 1, '#Spring - самый популярный и востребованный фреймворк для разработки на #java в последние годы. Занимает первое место ввиду его повышенного удобства в разработке комплексных web-приложений, требующих высокого качества. С его помощью разработчики с легкостью создают программные решения для крупных предприятий.', '2018-05-05 20:01:00', 'Спринг - самый популярный фреймворк.', 2, 1);
insert into posts values(2, 1, 'ACCEPTED', 2, '#Java в последние годы уверенно держится в топе самых популярных современных языков программирования, по данным TIOBE и других различных независимых ресурсов.', '2019-05-07 20:01:00', 'Java в последние годы все еще в топе.', 3, 2);
insert into posts values(3, 1, 'ACCEPTED', 1, 'Фреймворк Hibernate является в Java реализацией JPA в #Spring Boot. JDBC - это стандарт доступа к базам данных, JPA - это стандарт персистентности, Hibernate - это реализующий его ORM, Spring Data - это механизм организации репозиториев, а репозиторий - это абстракция, лежащая на уровень выше ORM. То есть Spring Data использует Hibernate, а Hibernate использует JDBC.', '2020-05-09 21:01:00', 'Фреймворк Hibernate является реализацией JPA в Spring Boot.', 4, 3);
insert into posts values(4, 1, 'ACCEPTED', 2, 'Миссия Skillbox — дать возможность каждому быть актуальным и востребованным специалистом прямо сейчас. Вне зависимости от возраста и географии. Предлагается самый большой выбор курсов дополнительного профессионального образования.', '2021-05-11 03:01:00', 'Онлайн-университет Skillbox — Миссия выполнима ?', 1, 4);
insert into posts values(5, 1, 'ACCEPTED', 1, 'HeadHunter — один из самых крупных сайтов по поиску работы и сотрудников в мире (по данным рейтинга Similarweb). Мы создаем передовые технологии на всех доступных платформах для того, чтобы работодатели могли быстро найти подходящего сотрудника, а соискатели — хорошую работу. Наши мобильные приложения стабильно занимают первые места в категории «Бизнес» на всех платформах.', '2021-05-11 11:55:55', 'HeadHunter — один из самых крупных сайтов по поиску работы и сотрудников в мире.', 3, 4);
