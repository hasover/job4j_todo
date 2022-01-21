# job4j_todo
[![Build Status](https://app.travis-ci.com/hasover/job4j_todo.svg?branch=master)](https://app.travis-ci.com/hasover/job4j_todo)

* [Описание](#описание)
* [Технологии](#технологии)
* [Функционал](#функционал)
* [Контакты](#контакты)

## Описание
Приложение имеет одну страницу со списком дел index.html, форму регистрации и авторизации.
Если дело сделано, то его отмечают, как выполненное и оно исчезает из списка.
Для каждого дела можно выбрать несколько категорий.

## Технологии
* Java14
* PostgreSQL
* HQL
* Hibernate
* Servlet
* HTML, BOOTSTRAP, JS, Ajax
* Apache Tomcat
* Travis CI

## Функционал

### 1. Авторизация.
![alt text](https://github.com/hasover/job4j_todo/blob/master/images/auth.PNG)
### 2. Регистрация.
![alt text](https://github.com/hasover/job4j_todo/blob/master/images/reg.PNG)
### 3. Добавление нового дела.
![alt text](https://github.com/hasover/job4j_todo/blob/master/images/3.PNG)
### 4. Просмотр списка дел.
![alt text](https://github.com/hasover/job4j_todo/blob/master/images/4.PNG)
### 5. Просмотр списка дел включая выполненные.
![alt text](https://github.com/hasover/job4j_todo/blob/master/images/5.PNG)

## Сборка приложения
- Для сборки приложения на вашем компьютере должны быть установлены:
    - JDK 14+
    - Maven
    - PostgreSQL
    - Tomcat
- Укажите настройки для подключения к БД в файле `src/main/resources/hibernate.cfg.xml`
- Выполните скрипт `db/schema.sql`
- Выполните команду `mvn package`
- Файл `target/job4j_todo.war` скопируйте в webapp tomcat

Приложение будет доступно по адресу: http://localhost:8080/job4j_todo

## Контакты
telegram: @hasover
