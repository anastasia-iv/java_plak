INSERT INTO schedule (course_id, teacher_id, auditorium_id, weekday, time, sh_group, year) VALUES
   (1, 1, 1, 'Mon', (TIME '8:45:00'), 101, 1),
   (3, 2, 6, 'Mon', (TIME '10:30:00'), 101, 1),
   (4, 5, 2, 'Mon', (TIME '12:50:00'), 101, 1),
   (2, 1, 4, 'Mon', (TIME '14:25:00'), 101, 1),
   (2, 1, 4, 'Mon', (TIME '14:25:00'), 103, 1),
   (2, 1, 1, 'Mon', (TIME '14:25:00'), 102, 1),
   (3, 5, 9, 'Mon', (TIME '10:30:00'), 102, 1),
   (3, 5, 9, 'Mon', (TIME '12:50:00'), 103, 1),
   (3, 2, 1, 'Tue', (TIME '8:45:00'), 101, 1),
   (2, 1, 4, 'Wed', (TIME '8:45:00'), 101, 1),
   (2, 1, 4, 'Wed', (TIME '8:45:00'), 102, 1),
   (2, 1, 4, 'Wed', (TIME '8:45:00'), 103, 1),
   (6, 6, 18, 'Thu', (TIME '8:45:00'), 101, 1),
   (5, 7, 16, 'Fri', (TIME '8:45:00'), 101, 1);

INSERT INTO teacher_courses (course_id, teacher_id) VALUES
    (1, 1),
    (2, 1),
    (3, 2),
    (3, 5),
    (4, 5),
    (6, 6),
    (5, 7);

