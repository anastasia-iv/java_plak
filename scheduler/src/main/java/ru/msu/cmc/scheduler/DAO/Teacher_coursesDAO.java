package ru.msu.cmc.scheduler.DAO;

import ru.msu.cmc.scheduler.models.Course;
import ru.msu.cmc.scheduler.models.Teacher;
import ru.msu.cmc.scheduler.models.Teacher_courses;

public interface Teacher_coursesDAO extends CommonDAO<Teacher_courses, Integer> {
    Teacher_courses findByTeacherAndCourse(Teacher teacher, Course course);
}