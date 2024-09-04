insert into employees(age, joined, work_experience_years, description, name)
values (21,'2024-08-23',3,'Сложный в общении','Годрик Сторукий'),
       (23,'2022-08-22',3,'бывший логопед','Мирон Янович'),
       (24,'2024-09-08',3,'дед тоже Саня','Сан Саныч'),
       (25,'2024-11-08',3,'понять и простить','Бородач Гриша'),
       (26,'2024-12-08',3,'убивает взглядом','Мария Сергеева');

insert into orders(title, created, description, order_employ_id)
values ('Отчёт о проделанной работе', LOCALTIMESTAMP, 'покрасил кнопку',1),
       ('Отчёт о ревью', LOCALTIMESTAMP, 'кандидат путает квадроточие с квадрокоптером',3),
       ('Отчёт о проекте', LOCALTIMESTAMP, 'проект закончен',2),
       ('Отчёт о прохождении Elden Ring', LOCALTIMESTAMP, 'где то на середине',5);


insert into tasks(title, created, description, who_gave_task_id, who_given_task_id)
values ('Кнопка', LOCALTIMESTAMP, 'покрась кнопку пжпжп',1,3),
       ('миграция бд', LOCALTIMESTAMP, 'переделай на ликвидбейс',3,1),
       ('Кофе', LOCALTIMESTAMP, 'сходи за кофе',2,3),
       ('Кофе', LOCALTIMESTAMP, 'сам сходи',3,2),
       ('Интерфейс', LOCALTIMESTAMP, 'ты про тимлиф знаешь? погугли',2,3),
       ('Куллер', LOCALTIMESTAMP, 'Поменяй бак с водой',5,4);


insert into users(active, password, username,employee_id)
values (True, '$2a$08$BcpUx.x3wyjViG2BEOIb5e4H4xMVyFVVhUbHwCEWNNWZCpXsQoNX.', 'godrik',1),
       (True, '$2a$08$BcpUx.x3wyjViG2BEOIb5e4H4xMVyFVVhUbHwCEWNNWZCpXsQoNX.', 'miron',2),
       (True, '$2a$08$BcpUx.x3wyjViG2BEOIb5e4H4xMVyFVVhUbHwCEWNNWZCpXsQoNX.', 'SanSan',3),
       (True, '$2a$08$BcpUx.x3wyjViG2BEOIb5e4H4xMVyFVVhUbHwCEWNNWZCpXsQoNX.', 'Grisha001',4),
       (True, '$2a$08$BcpUx.x3wyjViG2BEOIb5e4H4xMVyFVVhUbHwCEWNNWZCpXsQoNX.', 'mashasha',5);


insert into user_account_roles(user_account_id, roles)
values  (1,'ROLE_USER'),
        (2,'ROLE_USER'),
        (3,'ROLE_USER'),
        (4,'ROLE_USER'),
        (5,'ROLE_ADMIN');
