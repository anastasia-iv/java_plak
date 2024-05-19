DROP TABLE IF EXISTS sсhedule CASCADE;
CREATE TABLE schedule (
    cell_id  SERIAL PRIMARY KEY,
    tc_id integer,
    auditorium_id integer,
    weekday varchar,
    "time" time,
    sh_group integer,
    "year" integer,
    FOREIGN KEY (tc_id) REFERENCES teacher_courses(tc_id) ON DELETE CASCADE,
    FOREIGN KEY (auditorium_id) REFERENCES auditorium(auditorium_id) ON DELETE CASCADE
);
