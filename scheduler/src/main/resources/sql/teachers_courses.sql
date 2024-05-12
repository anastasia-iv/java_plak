DROP TABLE IF EXISTS teacher_courses CASCADE;
CREATE TABLE teacher_courses (
    tc_id SERIAL PRIMARY KEY,
    teacher_id integer,
    course_id integer,
    FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE,
    FOREIGN KEY (teacher_id) REFERENCES teachers(teacher_id) ON DELETE CASCADE
);
