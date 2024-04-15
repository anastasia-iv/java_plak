package ru.msu.cmc.scheduler.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.scheduler.DAO.CourseDAO;
import ru.msu.cmc.scheduler.models.Course;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import jakarta.persistence.TypedQuery;

import java.util.List;

@Repository
public class CourseDAOImpl extends CommonDAOImpl<Course, Long> implements CourseDAO {

    public CourseDAOImpl() {
        super();
        setEntityClass(Course.class);
    }

    @Override
    public List<Course> getAllCourseByName(String CourseName) {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<Course> query = session.createQuery("FROM Course WHERE name LIKE :gotName", Course.class)
            query.setParameter("gotName", likeExpr(CourseName));
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public Course getSingleCourseByName(String CourseName) {
        List<Course> candidates = this.getAllCourseByName(CourseName);
        return candidates == null ? null : candidates.get(0);
    }


    @Override
    public List<Course> getExtraCourses() {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<Course> query = session.createQuery("SELECT Course FROM Course WHERE Course.extra_course = :ExtraCourses", Course.class);
            query.setParameter("ExtraCourses", "extra");
            return query.getResultList();
        }
    }

}