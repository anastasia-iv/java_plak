package ru.msu.cmc.scheduler.DAO.impl;
import ru.msu.cmc.scheduler.models.*;
import ru.msu.cmc.scheduler.utils.*;
import org.hibernate.Transaction;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.scheduler.DAO.StudentDAO;
import ru.msu.cmc.scheduler.models.Student;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import jakarta.persistence.TypedQuery;

import java.util.List;

@Repository
public class StudentDAOImpl extends CommonDAOImpl<Student, Integer> implements StudentDAO {

    public StudentDAOImpl() {
        super();
        setEntityClass(Student.class);
    }

    @Override
    public List<Student> getAllStudentsByName(String StudentName) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();) {
            TypedQuery<Student> query = session.createQuery("SELECT st FROM Student st WHERE st.name = :StudentName", Student.class);
            query.setParameter("StudentName", StudentName);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public Student getSingleStudentByName(String StudentName) {
        List<Student> candidates = this.getAllStudentsByName(StudentName);
        return candidates == null ? null : candidates.get(0);
    }


    @Override
    public List<Student> getStudentByFilter(int stGroup, int stFlow, int year) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();) {
            TypedQuery<Student> query = session.createQuery("SELECT st FROM Student st WHERE st.group = :stGroup AND st.flow = :stFlow AND st.year = :year", Student.class);
            query.setParameter("stGroup", stGroup);
            query.setParameter("stFlow", stFlow);
            query.setParameter("year", year);
            return query.getResultList();
        }
    }

}