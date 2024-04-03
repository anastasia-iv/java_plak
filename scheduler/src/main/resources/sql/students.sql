DROP TABLE IF EXISTS students CASCADE;
CREATE TABLE students (
    student_id SERIAL PRIMARY KEY,
    student_name varchar,
    st_flow integer,
    st_group integer,
    year integer,
    average float,
    portfolio varchar
);
