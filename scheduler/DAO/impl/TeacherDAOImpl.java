package ru.msu.cmc.scheduler.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.scheduler.DAO.StudentDAO;
import ru.msu.cmc.scheduler.models.Student;

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
public class TeacherDAOImpl extends CommonDAOImpl<Teacher, Long> implements TeacherDAO {

    public TeacherDAOImpl() {
        super();
        setEntityClass(Teacher.class);
    }

    @Override
    public List<Teacher> getAllTeacherByName(String TeacherName) {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<Teacher> query = session.createQuery("FROM Teacher WHERE name LIKE :gotName", Teacher.class)
            query.setParameter("gotName", likeExpr(TeacherName));
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public Teacher getSingleTeacherByName(String TeacherName) {
        List<Teacher> candidates = this.getAllTeacherByName(TeacherName);
        return candidates == null ? null : candidates.get(0);
    }


    @Override
    public List<Teacher> getTeachersByCathedra(String Cathedra) {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<Teacher> query = session.createQuery("SELECT Teacher FROM Teacher WHERE Teacher.name = :Cathedra", Teacher.class);
            query.setParameter("cathedraTeachers", filmTitle);
            return query.getResultList();
        }
    }
}