package ru.msu.cmc.scheduler.DAO.impl;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.transaction.Transactional;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.msu.cmc.scheduler.DAO.CommonDAO;
import ru.msu.cmc.scheduler.models.CommonEntity;
import ru.msu.cmc.scheduler.utils.HibernateSessionFactoryUtil;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Component
public class CommonDAOImpl<T extends CommonEntity<ID>, ID> implements CommonDAO<T, ID> {
    @Autowired
    protected SessionFactory sessionFactory;

    @Setter
    private Class<T> entityClass;

    @Override
    @Transactional
    public void save(T entity) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
            session.beginTransaction();
            session.merge(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    @Transactional
    public void update(T entity) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
            session.beginTransaction();
            session.merge(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    @Transactional
    public void delete(T entity) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
            session.beginTransaction();
            session.remove(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    @Transactional
    public void saveCollection(Collection<T> entities) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
            session.beginTransaction();
            for (T entity : entities) {
                this.save(entity);
            }
            session.getTransaction().commit();
        }
    }

    @Override
//    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public T getById(ID id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
            return session.get(entityClass, (Serializable) id);
        }
    }

    @Override
    public List<T> getAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
            CriteriaQuery<T> criteriaQuery = session.getCriteriaBuilder().createQuery(entityClass);
            criteriaQuery.from(entityClass);
            return session.createQuery(criteriaQuery).getResultList();
        }
    }
}