DROP TABLE IF EXISTS courses CASCADE;
CREATE TABLE courses (
    course_id SERIAL PRIMARY KEY,
    course_name varchar,
    course_type varchar,
    "year" integer,
    flow integer,
    extra_course varchar
);

INSERT INTO courses (course_name, course_type, "year", flow) VALUES
 ('Мат.анализ', 'seminar', 1, 1),
 ('Мат.анализ', 'lecture',  1, 1),
 ('Лин.алгебра', 'seminar', 1, 1),
 ('Алгоритмы', 'lecture', 1, 2),
 ('Сист.программирование', 'seminar', 1, 1),
 ('Ассемблер','seminar', 1, 1),
 ('Механика', 'lecture', 2, 2),
 ('Электродинамика', 'lecture', 2, 2),
 ('Теор.вероятности', 'lecture', 2, 2),
 ('Дифф.уравнения', 'lecture', 2, 2),
 ('Мат.статистика', 'lecture', 2, 2);