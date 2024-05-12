package ru.msu.cmc.scheduler.DAO.impl;

import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.scheduler.DAO.TeacherDAO;
import ru.msu.cmc.scheduler.models.Teacher;
import ru.msu.cmc.scheduler.utils.HibernateSessionFactoryUtil;

import java.util.List;

@Repository
public class TeacherDAOImpl extends CommonDAOImpl<Teacher, Integer> implements TeacherDAO {

    public TeacherDAOImpl() {
        super();
        setEntityClass(Teacher.class);
    }

    @Override
    public List<Teacher> getAllTeachersByName(String TeacherName) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            TypedQuery<Teacher> query = session.createQuery("SELECT t FROM Teacher t WHERE t.name = :TeacherName", Teacher.class);
            query.setParameter("TeacherName", TeacherName);
            return query.getResultList().isEmpty() ? null : query.getResultList();
        }
    }

    @Override
    public Teacher getSingleTeacherByName(String TeacherName) {
        List<Teacher> candidates = this.getAllTeachersByName(TeacherName);
        return candidates == null ? null : candidates.get(0);
    }


    @Override
    @Transactional
    public List<Teacher> getTeachersByCathedra(String Cathedra) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            TypedQuery<Teacher> query = session.createQuery("SELECT t FROM Teacher t WHERE t.cathedra = :Cathedra", Teacher.class);

            query.setParameter("Cathedra", Cathedra);
            List<Teacher> resultList = query.getResultList();
            return resultList;
        }
    }
}