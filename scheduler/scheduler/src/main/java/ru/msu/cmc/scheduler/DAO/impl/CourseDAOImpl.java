package ru.msu.cmc.scheduler.DAO.impl;
import ru.msu.cmc.scheduler.models.*;
import ru.msu.cmc.scheduler.utils.*;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.scheduler.DAO.CourseDAO;
import ru.msu.cmc.scheduler.models.Course;

import java.util.List;

import jakarta.persistence.TypedQuery;
import ru.msu.cmc.scheduler.utils.HibernateSessionFactoryUtil;

@Repository
public class CourseDAOImpl extends CommonDAOImpl<Course, Integer> implements CourseDAO {

    public CourseDAOImpl() {
        super();
        setEntityClass(Course.class);
    }

    @Override
    public List<Course> getAllCoursesByName(String CourseName) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();) {
            TypedQuery<Course> query = session.createQuery("SELECT c FROM Course c WHERE c.name = :CourseName", Course.class);
            query.setParameter("CourseName", CourseName);
            return query.getResultList().isEmpty() ? null : query.getResultList();
        }
    }

    @Override
    public Course getSingleCourseByName(String CourseName) {
        List<Course> candidates = this.getAllCoursesByName(CourseName);
        return candidates == null ? null : candidates.get(0);
    }


    @Override
    public List<Course> getExtraCourses() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();) {
            TypedQuery<Course> query = session.createQuery("SELECT Course FROM Course WHERE Course.extra_course = :ExtraCourses", Course.class);
            query.setParameter("ExtraCourses", "extra");
            return query.getResultList();
        }
    }

}