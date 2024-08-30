insert into employees(age, joined, work_experience_years, description, name, post)
values (21,'2024-08-23',3,'Сложный в общении','Годрик Сторукий', 'admin'),
       (23,'2022-08-22',3,'бывший логопед','Мирон Янович', 'прогер'),
       (24,'2024-09-08',3,'дед тоже Саня','Сан Саныч', 'admin'),
       (25,'2024-11-08',3,'понять и простить','Бородач Гриша', 'прогер'),
       (26,'2024-12-08',3,'убивает взглядом','Мария Сергеева', 'admin');

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


