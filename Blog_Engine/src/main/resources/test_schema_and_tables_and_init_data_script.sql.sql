-- MySQL dump 10.13  Distrib 8.0.15, for Linux (x86_64)
-- ------------------------------------------------------
-- Host: localhost    Database: blog_engine
-- ------------------------------------------------------
-- Server version	8.0.15
-- ------------------------------------------------------

DROP DATABASE IF EXISTS blog_engine;
CREATE DATABASE blog_engine CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE blog_engine;

--
-- Table structure for table `users`
--
CREATE TABLE users
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    is_moderator TINYINT NOT NULL DEFAULT 0,
    reg_time datetime NOT NULL,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    code VARCHAR(255),
    photo TEXT
) ENGINE=InnoDB AUTO_INCREMENT=5;

--
-- Dumping data for table `users`
--
insert into users values(1, 1, '2018-11-22 20:12:00', 'Mammoth', '123456@gmail.com', '123456', null, null);
insert into users values(2, 1, '2018-11-23 20:12:00', 'Omsk55', '987654@gmail.com', '987654', null, null);
insert into users values(3, 0, '2019-11-22 20:12:00', 'Morpheus', '000000@gmail.com', '000000', null, null);
insert into users values(4, 0, '2020-02-27 20:12:00', 'Trinity', '555555@gmail.com', '555555', null, null);

--
-- Table structure for table `posts`
--
CREATE TABLE posts
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    is_active TINYINT NOT NULL DEFAULT 1,
    moderation_status enum('NEW','ACCEPTED','DECLINED') NOT NULL DEFAULT 'NEW',
    moderator_id INT,
    text text NOT NULL,
    time datetime NOT NULL,
    title VARCHAR(255) NOT NULL,
    user_id INT NOT NULL,
    view_count INT NOT NULL,
    CONSTRAINT `user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6;

--
-- Dumping data for table `posts`
--
insert into posts values(1, 1, 'ACCEPTED', 1, '#Spring - самый популярный и востребованный фреймворк для разработки на #java в последние годы. Занимает первое место ввиду его повышенного удобства в разработке комплексных web-приложений, требующих высокого качества. С его помощью разработчики с легкостью создают программные решения для крупных предприятий.', '2018-05-05 20:01:00', 'Спринг - самый популярный фреймворк.', 2, 1);
insert into posts values(2, 1, 'ACCEPTED', 2, '#Java в последние годы уверенно держится в топе самых популярных современных языков программирования, по данным TIOBE и других различных независимых ресурсов.', '2019-05-07 20:01:00', 'Java в последние годы все еще в топе.', 3, 2);
insert into posts values(3, 1, 'ACCEPTED', 1, 'Фреймворк Hibernate является в Java реализацией JPA в #Spring Boot. JDBC - это стандарт доступа к базам данных, JPA - это стандарт персистентности, Hibernate - это реализующий его ORM, Spring Data - это механизм организации репозиториев, а репозиторий - это абстракция, лежащая на уровень выше ORM. То есть Spring Data использует Hibernate, а Hibernate использует JDBC.', '2020-05-09 21:01:00', 'Фреймворк Hibernate является реализацией JPA в Spring Boot.', 4, 3);
insert into posts values(4, 1, 'ACCEPTED', 2, 'Миссия Skillbox — дать возможность каждому быть актуальным и востребованным специалистом прямо сейчас. Вне зависимости от возраста и географии. Предлагается самый большой выбор курсов дополнительного профессионального образования.', '2021-05-11 03:01:00', 'Онлайн-университет Skillbox — Миссия выполнима ?', 1, 4);
insert into posts values(5, 1, 'ACCEPTED', 1, 'HeadHunter — один из самых крупных сайтов по поиску работы и сотрудников в мире (по данным рейтинга Similarweb). Мы создаем передовые технологии на всех доступных платформах для того, чтобы работодатели могли быстро найти подходящего сотрудника, а соискатели — хорошую работу. Наши мобильные приложения стабильно занимают первые места в категории «Бизнес» на всех платформах.', '2021-05-11 11:55:55', 'HeadHunter — один из самых крупных сайтов по поиску работы и сотрудников в мире.', 3, 4);

--
-- Table structure for table `post_votes`
--
CREATE TABLE post_votes
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    post_id INT NOT NULL,
    time DATETIME NOT NULL,
    value TINYINT NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7;

--
-- Dumping data for table post_votes
--
insert into post_votes values(1, 1, 1, '2021-05-15 03:22:33', 1);
insert into post_votes values(2, 2, 1, '2021-05-15 01:11:29', 1);
insert into post_votes values(3, 3, 3, '2021-05-15 02:31:00', 1);
insert into post_votes values(4, 4, 4, '2021-05-15 02:38:00', -1);
insert into post_votes values(5, 1, 5, '2021-05-15 02:31:00', 1);
insert into post_votes values(6, 4, 5, '2021-05-15 02:31:00', -1);

--
-- Table structure for table `tags`
--
CREATE TABLE tags
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6;

--
-- Dumping data for table `tags`
--
insert into tags values(1, 'JAVA');
insert into tags values(2, 'SPRING');
insert into tags values(3, 'HIBERNATE');
insert into tags values(4, 'SKILLBOX');
insert into tags values(5, 'HEADHUNTER');

--
-- Table structure for table `tag2post`
--
CREATE TABLE tag2post
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    post_id INT NOT NULL,
    tag_id INT NOT NULL,
    CONSTRAINT `post` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`),
    CONSTRAINT `tag` FOREIGN KEY (`tag_id`) REFERENCES `tags` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9;

--
-- Dumping data for table `tag2post`
--
insert into tag2post values(1, 1, 1);
insert into tag2post values(2, 3, 2);
insert into tag2post values(3, 3, 3);
insert into tag2post values(4, 4, 4);
insert into tag2post values(5, 2, 1);
insert into tag2post values(6, 5, 5);
insert into tag2post values(7, 1, 2);
insert into tag2post values(8, 3, 1);

--
-- Table structure for table `post_comments`
--
CREATE TABLE post_comments
(
    Id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    parent_id INT DEFAULT NULL,
    post_id INT NOT NULL,
    user_id INT NOT NULL,
    time DATETIME NOT NULL,
    text TEXT NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8;

--
-- Dumping data for table `post_comments`
--
insert into post_comments values(1, null, 5, 2, '2021-05-14 11:33:00', 'Its true )))');
insert into post_comments values(2, null, 2, 2, '2021-05-14 14:44:22', 'May be NOT... )');
insert into post_comments values(3, null, 3, 3, '2021-05-14 14:55:00', 'EXACTLY !!!');
insert into post_comments values(4, null, 4, 4, '2021-05-14 19:12:11', 'I am still HERE... )))');
insert into post_comments values(5, null, 4, 3, '2021-05-15 09:12:00', 'I am almost searching for work ! )))');
insert into post_comments values(6, null, 3, 4, '2021-05-15 11:55:11', 'May be YES... )');
insert into post_comments values(7, null, 3, 1, '2021-05-15 13:55:00', 'Its impossible !!!');

--
-- Table structure for table `captcha_codes`
--
CREATE TABLE captcha_codes
(
    Id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    time DATETIME NOT NULL,
    code TINYTEXT NOT NULL,
    secret_code TINYTEXT NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4;

--
-- Dumping data for table `captcha_codes`
--
insert into captcha_codes values(1, '2021-05-14 23:21:00', 'SpRiNg00000000000000', 'JaVa');
insert into captcha_codes values(2, '2021-05-24 23:21:00', '321321321321321321', 'OteLLo');
insert into captcha_codes values(3, '2021-06-06 00:12:00', 'роылвоарлоыпаоылвпаыв', 'SpringBOOT');

--
-- Table structure for table `global_settings`
--
CREATE TABLE global_settings
(
    Id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    value VARCHAR(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4;

--
-- Dumping data for table `global_settings`
--
insert into global_settings values(1, 'MULTIUSER_MODE', 'Многопользовательский режим', 'NO');
insert into global_settings values(2, 'POST_PREMODERATION', 'Премодерация постов', 'YES');
insert into global_settings values(3, 'STATISTICS_IS_PUBLIC', 'Показывать всем статистику блога', 'YES');
