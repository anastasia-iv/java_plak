package ru.msu.cmc.scheduler.DAO.impl;

import org.springframework.stereotype.Repository;
import ru.msu.cmc.scheduler.DAO.Student_coursesDAO;
import ru.msu.cmc.scheduler.models.Student_courses;

@Repository
public class Student_coursesDAOImpl extends CommonDAOImpl<Student_courses, Integer> implements Student_coursesDAO {

    public Student_coursesDAOImpl() {
        super();
        setEntityClass(Student_courses.class);
    }
}