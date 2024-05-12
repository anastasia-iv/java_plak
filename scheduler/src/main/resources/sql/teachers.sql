DROP TABLE IF EXISTS teachers CASCADE;
CREATE TABLE teachers(
    teacher_id SERIAL PRIMARY KEY,
    teacher_name varchar,
    mail varchar,
    cathedra varchar,
    academic_title varchar
);
