insert into employees(age, joined, work_experience_years, description, name)
values (21,'2024-08-23',3,'Сложный в общении','Годрик Сторукий'),
       (23,'2022-08-22',3,'бывший логопед','Мирон Янович'),
       (24,'2024-09-08',3,'дед тоже Саня','Сан Саныч'),
       (25,'2024-11-08',3,'понять и простить','Бородач Гриша'),
       (26,'2024-12-08',3,'убивает взглядом','Мария Сергеева');

insert into reports(title, created, description, report_employ_id, updated)
values ('Отчёт о проделанной работе', '2024-09-11T10:30:00', 'покрасил кнопку',1,LOCALTIMESTAMP),
       ('Отчёт о ревью', '2024-09-11T10:30:00', 'кандидат путает квадроточие с квадрокоптером',3,LOCALTIMESTAMP),
       ('Отчёт о проекте', '2024-09-11T10:30:00', 'проект закончен',2,LOCALTIMESTAMP),
       ('Отчёт о прохождении Elden Ring', '2024-09-11T10:30:00', 'где то на середине',5,LOCALTIMESTAMP);

insert into tasks(title, created, description, who_gave_task_id, who_given_task_id, status, updated,time_Allowed,report_id)
values ('Кнопка', '2024-09-11T10:30:00', 'покрась кнопку пжпжп',1,3,'CREATED',LOCALTIMESTAMP,8,1),
       ('миграция бд', '2024-09-11T10:30:00', 'переделай на ликвидбейс',3,1,'IN_PROGRESS',LOCALTIMESTAMP,10,3),
       ('Кофе', '2024-09-11T10:30:00', 'сходи за кофе',2,3,'CREATED',LOCALTIMESTAMP,13,2),
       ('Кофе', '2024-09-11T10:30:00', 'сам сходи',3,2,'COMPLETED',LOCALTIMESTAMP,9,2),
       ('Интерфейс', '2024-09-11T10:30:00', 'ты про тимлиф знаешь? погугли',2,3,'CANCELLED',LOCALTIMESTAMP,1,1),
       ('Куллер', '2024-09-11T10:30:00', 'Поменяй бак с водой',5,4,'CANCELLED',LOCALTIMESTAMP,2,4);


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
