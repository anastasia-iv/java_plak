DROP FUNCTION man_fio_gen();
DROP FUNCTION woman_fio_gen();

select
    a.auditorium_id,
    a.auditorium_name,
    a.capacity
from
    auditorium a;

CREATE OR REPLACE FUNCTION achievements_gen()
    RETURNS TEXT AS $$
DECLARE
    levels TEXT[];
    type_of_event TEXT[];
    num_of_events INT;
    achievement TEXT:='';
BEGIN
    levels := ARRAY['Всемирн', 'Национальн', 'Муниципаль', 'Учебн', 'Профессиональн'];
    type_of_event := ARRAY['ая олипмиада', 'ый хакатон', 'ая конференция', 'ый вебинар', 'ое соревнование', 'ый митап'];
    num_of_events := ceil(random()*4);

    FOR i IN 1..num_of_events LOOP
            IF I = num_of_events THEN
                achievement :=  achievement || levels[ceil(random() * 5)] || type_of_event[ceil(random() * 6)] || '.' ;
            ELSE
                achievement :=  achievement || levels[ceil(random() * 5)] || type_of_event[ceil(random() * 6)] || ',' ;
            END IF;
        END LOOP;

    RETURN achievement;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION man_fio_gen()
    RETURNS TEXT AS $$
DECLARE
    fio TEXT;
    name TEXT;
    surname TEXT;
    dad TEXT;
BEGIN
    surname := ('{Беспалов, Кузнецов, Котов, Федоров, Волков, Артемов, Пантелеев, Панов, Морозов, Борисов, Федосеев, Балашов, Антонов, Булатов, Зиновьев, Петров, Дмитриев}'::text[])[ceil(random()*17)];
    name := ('{Вячеслав, Давид, Александр, Роман, Платон, Олег, Лев, Марк, Макар, Юрий}'::text[])[ceil(random()*10)];
    dad := ('{Романович, Давидович, Владиславович, Николаевич, Захарович, Русланович, Никитович, Феликсович, Святославович, Николаевич, Федорович, Андреевич, Тимофеевич}'::text[])[ceil(random()*13)];
    fio = surname || ' ' || name || ' ' || dad;
    RETURN fio;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION woman_fio_gen()
    RETURNS TEXT AS $$
DECLARE
    fio TEXT;
    name TEXT;
    surname TEXT;
    dad TEXT;
BEGIN
    surname := ('{Беспалова, Минина, Кузнецова, Котова, Федорова, Волкова, Анисимова, Артемова, Пантелеева, Панова, Степанова, Морозова, Борисова, Федосеева, Балашова, Антонова, Булатова, Зиновьева, Петрова, Дмитриева}'::text[])[ceil(random()*20)];
    name := ('{Вячеслава, Анастасия, Елизавета, Вероника, София, Арина, Дарья, Мария, Анна, Майя}'::text[])[ceil(random()*10)];
    dad := ('{Романовна, Давидовна, Владиславовна, Матвеевна, Викторовна, Платоновна, Матвеевна, Николаевна, Захаровна, Руслановна, Феликсовна}'::text[])[ceil(random()*11)];
    fio := surname || ' ' || name || ' ' || dad;
    RETURN fio;
END;
$$ LANGUAGE plpgsql;

INSERT INTO students (student_name, st_flow, st_group, year, average, portfolio)
SELECT
    man_fio_gen(),
    (random() * 2 + 1)::integer,
    ((random() * 6 + 1)*100 + (random() * 19) + 1)::integer,
    (random()*10 + 1999)::integer,
    ROUND(((random()*301 + 200)/100)::numeric, 2)::float,
    achievements_gen()
FROM generate_series(1, 15);

INSERT INTO students (student_name, st_flow, st_group, year, average, portfolio)
SELECT
    man_fio_gen(),
    --(random() * 3)::integer,
    --((random() * 6 + 1)*100 + (random() * 19) + 1)::integer,
    1,
    (ARRAY[101, 102, 103])[ceil(random()*3)],
    (random()*10 + 1999)::integer,
    ((random()*301 + 200)/100)::float,
    achievements_gen()
FROM generate_series(1, 15);

INSERT INTO students (student_name, st_flow, st_group, year, average, portfolio)
SELECT
    woman_fio_gen(),
    --(random() * 3)::integer,
    --((random() * 6 + 1)*100 + (random() * 19) + 1)::integer,
    1,
    (ARRAY[101, 102, 103])[ceil(random()*3)],
    (random()*10 + 1999)::integer,
    ((random()*301 + 200)/100)::float,
    achievements_gen()
FROM generate_series(1, 15);

DROP FUNCTION title();
CREATE OR REPLACE FUNCTION title()
    RETURNS TEXT AS $$
DECLARE
    title TEXT;
BEGIN
    title := ('{‘Кандидат математических наук‘, ‘Доктор математических наук‘, ‘Профессор‘, ‘Доцент‘, ‘Старший преподаватель‘, ‘Младший научный сотрудник‘, ‘Преподаватель‘}'::text[])[ceil(random()*7)];
    RETURN title;
END;
$$ LANGUAGE plpgsql;

INSERT INTO teachers (teacher_name, mail, cathedra, academic_title)
SELECT
    woman_fio_gen(),
    'teacher_msu'||(random() * 1234)::integer::varchar,
    (ARRAY['МФ', 'ВТМ', 'OM', 'ВМ', 'ИБ', 'ММП', 'МС', 'МК', 'ИО', 'СП', 'ОУ', 'АСВК', 'ИИТ'])[ceil(random()*13)],
    title()
FROM generate_series(1, 15);

DO $$
    DECLARE
        type TEXT;
        cap INTEGER;
    BEGIN
        FOR I IN 1..18 LOOP
                type := ('{лекционная, семинарская}'::text[])[ceil(random()*2)];
                IF type = 'лекционная' THEN
                    cap := (random() * 200 + 100)::integer;
                ELSE
                    cap := (random() * 30 + 10)::integer;
                END IF;
                INSERT INTO auditorium (auditorium_name, capacity)
                VALUES (type, cap);
            END LOOP;
    END;
$$;

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