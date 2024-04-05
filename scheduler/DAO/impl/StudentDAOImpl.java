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

@Repository
public class StudentDAOImpl extends CommonDAOImpl<Student, Long> implements StudentDAO {

    public StudentDAOImpl(){
        super(Student.class);
    }

    @Override
    public List<Student> getAllStudentByName(String StudentName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Person> query = session.createQuery("FROM Person WHERE name LIKE :gotName", Student.class)
                    .setParameter("gotName", likeExpr(StudentName));
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public Student getSingleStudentByName(String StudentName) {
        List<Student> candidates = this.getAllStudentByName(StudentName);
        return candidates == null ? null : candidates.get(0);
    }


    @Override
    public List<Student> getByFilter(int stGroup, int stFlow, int year) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Student> criteriaQuery = builder.createQuery(Student.class);
            Root<Student> root = criteriaQuery.from(Student.class);

            List<Predicate> predicates = new ArrayList<>();
            if (stGroup != 0) {
                predicates.add(builder.equal(root.get("group"), stGroup));
            }
            if (stFlow != 0) {
                predicates.add(builder.equal(root.get("flow"), stFlow));
            }
            if (year != 0) {
                predicates.add(builder.equal(root.get("year"), year));
            }

            criteriaQuery.where(predicates.toArray(new Predicate[0]));

            return session.createQuery(criteriaQuery).getResultList();
        }
    }

}