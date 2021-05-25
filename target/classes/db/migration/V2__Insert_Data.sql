INSERT INTO users ( user_id, name, password, email, is_moderator)
VALUES (1, 'Alex', '123', 'mail@mail.ru', 1),
        (2, 'Valera', '123', 'valera@mail.ru', 1),
        (3, 'Petr', '123', 'petr@mail.ru', 0);

INSERT INTO posts (is_active, moderator_id, text, title, user_id, time, view_count)
VALUES (1, 1, 'Some text', 'Coder life', 1, '2020-04-04 20:00:00', 1),
       (1, 1, 'For all Spring applications, you should start with the Spring Initializr. The Initializr offers a fast way to pull in all the dependencies you need for an application and does a lot of the setup for you. This example needs only the Spring Web dependency.', 'Starting with Spring Initialize', 1, '2020-12-10 20:00:00', 5),
       (1, 2, 'Now that you have set up the project and build system, you can create your web service.
Begin the process by thinking about service interactions.', 'Create a Resource Representation Class', 2, '2020-04-04 20:00:00', 3),
       (0, 1, 'Some more text', 'Coder_and_bear', 3, '2020-08-20 12:00:00', 0),
       (1, 1, 'Some text', 'Coder_life', 1, '2020-04-04 22:00:00', 1),
       (1, 1, 'For all applications, we should start with the Spring Initializr. The Initializr offers a fast way to pull in all the dependencies you need for an application and does a lot of the setup for you. This example needs only the Spring Web dependency.', 'Starting with Spring Initialize', 1, '2020-12-10 20:00:00', 5),
       (1, 2, 'Now that you have set up the project and build system, you can create your web service.
The process by thinking about service interactions.', 'Create a Resource Representation Class', 2, '2020-04-04 20:00:00', 3),
       (0, 1, 'Some more text', 'Coder and bear', 3, '2020-08-20 10:00:00', 0),
       (1, 1, 'Some text', 'Coder life', 1, '2020-04-04 20:00:00', 1),
       (1, 1, 'For all Spring applications, you should start with the Spring Initializr. The Initializr offers a fast way to pull in all the dependencies you need for an application and does a lot of the setup for you. This example needs only the Spring Web dependency.', 'Starting with Spring Initialize', 1, '2020-12-10 20:00:00', 5),
       (1, 2, 'Now that you have set up the project and build system, you can create your web service.
Begin the process by thinking about service interactions.', 'Create a Resource Representation Class', 2, '2020-04-04 20:00:00', 3),
       (0, 1, '==========', '========', 3, '2020-08-20 10:05:00', 0);

INSERT INTO post_votes (time, value, post_id, user_id)
VALUES ( '2020-04-04 20:00:00', 1, 1, 1),
( '2020-04-04 20:00:00', 1, 1, 1),
( '2020-04-04 20:00:00', -1, 1, 1),
( '2020-04-04 20:00:00', -1, 2, 1),
( '2020-04-04 20:00:00', 1, 2, 1),
( '2020-04-04 20:00:00', 1, 2, 1),
( '2020-04-04 20:00:00', 1, 2, 1);

INSERT INTO tags(name)
VALUES ('Java'),('Spring'),('SQL'),('Postgre'),('Hibernate');

INSERT INTO tag2post (post_id, tag_id)
VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(2,1),(2,2),(2,3),
       (3,1),(3,2),(3,5),(4,1);