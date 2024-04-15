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
public class AuditoriumDAOImpl extends CommonDAOImpl<Student, Long> implements AuditoriumDAO {

    public AuditoriumDAOImpl() {
        super();
        setEntityClass(Auditorium.class);
    }
}