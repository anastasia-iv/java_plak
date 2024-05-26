package ru.msu.cmc.scheduler.DAO.impl;

import org.springframework.stereotype.Repository;
import ru.msu.cmc.scheduler.DAO.Teacher_coursesDAO;
import ru.msu.cmc.scheduler.models.Teacher_courses;

@Repository
public class Teacher_coursesDAOImpl extends CommonDAOImpl<Teacher_courses, Integer> implements Teacher_coursesDAO {

    public Teacher_coursesDAOImpl() {
        super();
        setEntityClass(Teacher_courses.class);
    }
}