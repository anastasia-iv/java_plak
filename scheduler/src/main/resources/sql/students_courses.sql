DROP TABLE IF EXISTS students_courses CASCADE;
CREATE TABLE student_courses (
    sc_id SERIAL PRIMARY KEY,
    student_id integer,
    course_id integer,
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE
);
