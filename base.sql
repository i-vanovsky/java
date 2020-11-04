--персонал
create table auto_personnel (
   id serial, 
   first_name VARCHAR(20) NOT NULL,
   last_name  VARCHAR(20) NOT NULL,
   pather_name VARCHAR(20) NOT NULL,
   PRIMARY KEY (id)
);

--автомобили
create table auto (
   id serial, 
   num VARCHAR(20) NOT NULL,
   color  VARCHAR(20) NOT NULL,
   mark VARCHAR(20) NOT NULL,
   personnel_id int,
   PRIMARY KEY (id),
   FOREIGN KEY (personnel_id) REFERENCES auto_personnel (id)  
);

--маршруты
create table routes (
   id serial, 
   name VARCHAR(50) NOT NULL,
   PRIMARY KEY (id)
);

--журнал 
create table journal (
   id serial, 
   time_out TIMESTAMP(0),
   time_in TIMESTAMP(0),
   auto_id int,
   route_id int,
   PRIMARY KEY (id),
   FOREIGN KEY (auto_id) REFERENCES auto (id),  
   FOREIGN KEY (route_id) REFERENCES routes (id) 
);

--заполнение маршрутов
INSERT INTO routes (name) VALUES ('пл.Кирова-Парк');
INSERT INTO routes (name) VALUES ('Автовокзал-Центр');
INSERT INTO routes (name) VALUES ('ул.Цветочная-ДК Горького');
INSERT INTO routes (name) VALUES ('Парк-Автовокзал');

--заполнение сотрудников
INSERT INTO auto_personnel (first_name, last_name, pather_name) VALUES ('Тверская', 'Елизавета', 'Федоровна');
INSERT INTO auto_personnel (first_name, last_name, pather_name) VALUES ('Лермонтов', 'Михаил', 'Юрьевич');
INSERT INTO auto_personnel (first_name, last_name, pather_name) VALUES ('Ахматова', 'Анна', 'Андреевна');
INSERT INTO auto_personnel (first_name, last_name, pather_name) VALUES ('Достоевский', 'Федор', 'Михайлович');
INSERT INTO auto_personnel (first_name, last_name, pather_name) VALUES ('Толстой', 'Лев', 'Николаевич');
INSERT INTO auto_personnel (first_name, last_name, pather_name) VALUES ('Тургенев', 'Иван', 'Сергеевич');
INSERT INTO auto_personnel (first_name, last_name, pather_name) VALUES ('Одинцова', 'Анна', 'Сергеевна');
INSERT INTO auto_personnel (first_name, last_name, pather_name) VALUES ('Гончаров', 'Иван', 'Александрович');
INSERT INTO auto_personnel (first_name, last_name, pather_name) VALUES ('Салтыков-Щедрин', 'Михаил', 'Евграфович');
INSERT INTO auto_personnel (first_name, last_name, pather_name) VALUES ('Ростова', 'Наталья', 'Ильинична');
INSERT INTO auto_personnel (first_name, last_name, pather_name) VALUES ('Каренина', 'Анна', 'Аркадьевна');
INSERT INTO auto_personnel (first_name, last_name, pather_name) VALUES ('Обломов', 'Илья', 'Ильич');
INSERT INTO auto_personnel (first_name, last_name, pather_name) VALUES ('Курагина', 'Елена', 'Васильевна');
INSERT INTO auto_personnel (first_name, last_name, pather_name) VALUES ('Вронский', 'Алексей', 'Кириллович');
INSERT INTO auto_personnel (first_name, last_name, pather_name) VALUES ('Болконский', 'Андрей', 'Николаевич');
INSERT INTO auto_personnel (first_name, last_name, pather_name) VALUES ('Болконская', 'Марья', 'Николаевна');
INSERT INTO auto_personnel (first_name, last_name, pather_name) VALUES ('Долохов', 'Федор', 'Иванович');
INSERT INTO auto_personnel (first_name, last_name, pather_name) VALUES ('Курагин', 'Ипполит', 'Васильевич');
INSERT INTO auto_personnel (first_name, last_name, pather_name) VALUES ('Курагин', 'Анатоль', 'Васильевич');
INSERT INTO auto_personnel (first_name, last_name, pather_name) VALUES ('Курагин', 'Василий', 'Сергеевич');
INSERT INTO auto_personnel (first_name, last_name, pather_name) VALUES ('Щербацкая', 'Екатерина', 'Александровна');
INSERT INTO auto_personnel (first_name, last_name, pather_name) VALUES ('Облонский', 'Степан', 'Аркадьевич');

--машины
INSERT INTO auto (num, color, mark, personnel_id) VALUES ('а123', 'Красный', 'Форд', 6);
INSERT INTO auto (num, color, mark, personnel_id) VALUES ('в222', 'Синий', 'Газель', 7);
INSERT INTO auto (num, color, mark, personnel_id) VALUES ('о132', 'Желтый', 'Мерседес', 6);
INSERT INTO auto (num, color, mark, personnel_id) VALUES ('к123', 'Зеленый', 'Форд', 19);
INSERT INTO auto (num, color, mark, personnel_id) VALUES ('к555', 'Красный', 'Мерседес', 15);
INSERT INTO auto (num, color, mark, personnel_id) VALUES ('с666', 'Синий', 'Форд', 11);
INSERT INTO auto (num, color, mark, personnel_id) VALUES ('х777', 'Желтый', 'Газель', 10);
INSERT INTO auto (num, color, mark, personnel_id) VALUES ('е432', 'Зеленый', 'Мерседес', 10);
INSERT INTO auto (num, color, mark, personnel_id) VALUES ('а990', 'Синий', 'Газель', 10);
INSERT INTO auto (num, color, mark, personnel_id) VALUES ('х665', 'Красный', 'Форд', 14);
INSERT INTO auto (num, color, mark, personnel_id) VALUES ('р391', 'Зеленый', 'Форд', 8);
INSERT INTO auto (num, color, mark, personnel_id) VALUES ('м321', 'Желтый', 'Газель', 9);
INSERT INTO auto (num, color, mark, personnel_id) VALUES ('н444', 'Красный', 'Газель', 20);
INSERT INTO auto (num, color, mark, personnel_id) VALUES ('а111', 'Желтый', 'Мерседес', 18);
INSERT INTO auto (num, color, mark, personnel_id) VALUES ('а908', 'Красный', 'Газель', 17);
INSERT INTO auto (num, color, mark, personnel_id) VALUES ('т287', 'Зеленый', 'Форд', 15);
INSERT INTO auto (num, color, mark, personnel_id) VALUES ('в888', 'Синий', 'Мерседес', 16);
INSERT INTO auto (num, color, mark, personnel_id) VALUES ('а723', 'Желтый', 'Газель', 12);
INSERT INTO auto (num, color, mark, personnel_id) VALUES ('а124', 'Синий', 'Газель', 12);
INSERT INTO auto (num, color, mark, personnel_id) VALUES ('р524', 'Зеленый', 'Мерседес', 16);
INSERT INTO auto (num, color, mark, personnel_id) VALUES ('с723', 'Красный', 'Газель', 13);
INSERT INTO auto (num, color, mark, personnel_id) VALUES ('т126', 'Красный', 'Форд', 13);

--Заполнение журнала
INSERT INTO journal (time_out, time_in, auto_id, route_id) 
VALUES (NOW()::timestamp(0), NOW()::timestamp(0)+INTERVAL '333 minutes', 1, 1);
INSERT INTO journal (time_out, time_in, auto_id, route_id) 
VALUES (NOW()::timestamp(0), NOW()::timestamp(0)+INTERVAL '222 minutes', 2, 2);
INSERT INTO journal (time_out, time_in, auto_id, route_id) 
VALUES (NOW()::timestamp(0), NOW()::timestamp(0)+INTERVAL '111 minutes', 3, 3);
INSERT INTO journal (time_out, time_in, auto_id, route_id) 
VALUES (NOW()::timestamp(0), NOW()::timestamp(0)+INTERVAL '123 minutes', 4, 1);
INSERT INTO journal (time_out, time_in, auto_id, route_id) 
VALUES (NOW()::timestamp(0), NOW()::timestamp(0)+INTERVAL '321 minutes', 5, 2);
INSERT INTO journal (time_out, time_in, auto_id, route_id) 
VALUES (NOW()::timestamp(0), NOW()::timestamp(0)+INTERVAL '134 minutes', 6, 3);
INSERT INTO journal (time_out, time_in, auto_id, route_id) 
VALUES (NOW()::timestamp(0), NOW()::timestamp(0)+INTERVAL '111 minutes', 7, 1);
INSERT INTO journal (time_out, time_in, auto_id, route_id) 
VALUES (NOW()::timestamp(0), NOW()::timestamp(0)+INTERVAL '222 minutes', 8, 2);
INSERT INTO journal (time_out, auto_id, route_id) 
VALUES (NOW()::timestamp(0), 9, 3);
INSERT INTO journal (time_out, auto_id, route_id) 
VALUES (NOW()::timestamp(0), 10, 1);
INSERT INTO journal (time_out, auto_id, route_id) 
VALUES (NOW()::timestamp(0), 12, 2);
INSERT INTO journal (time_out, auto_id, route_id) 
VALUES (NOW()::timestamp(0), 13, 3);
INSERT INTO journal (time_out, auto_id, route_id) 
VALUES (NOW()::timestamp(0), 14, 1);
INSERT INTO journal (time_out, auto_id, route_id) 
VALUES (NOW()::timestamp(0), 15, 2);
