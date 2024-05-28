package ru.msu.cmc.scheduler.DAO.impl;

import org.springframework.stereotype.Repository;
import ru.msu.cmc.scheduler.DAO.Teacher_coursesDAO;
import ru.msu.cmc.scheduler.models.Teacher_courses;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.scheduler.DAO.TeacherDAO;
import ru.msu.cmc.scheduler.models.Student;
import ru.msu.cmc.scheduler.utils.HibernateSessionFactoryUtil;
import ru.msu.cmc.scheduler.models.Course;
import ru.msu.cmc.scheduler.models.Teacher;

import java.util.List;

@Repository
public class Teacher_coursesDAOImpl extends CommonDAOImpl<Teacher_courses, Integer> implements Teacher_coursesDAO {

    public Teacher_coursesDAOImpl() {
        super();
        setEntityClass(Teacher_courses.class);
    }

    @Override
    public Teacher_courses findByTeacherAndCourse(Teacher teacher, Course course) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            TypedQuery<Teacher_courses> query = session.createQuery(
                    "FROM Teacher_courses tc WHERE tc.teacher = :teacher AND tc.course = :course", Teacher_courses.class);
            query.setParameter("teacher", teacher);
            query.setParameter("course", course);
            List<Teacher_courses> result = query.getResultList();
            return result.isEmpty() ? null : result.get(0);
        }
    }

    @Override
    public List<Course> findCoursesByTeacherId(Integer teacherId) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            TypedQuery<Course> query = session.createQuery(
                    "SELECT tc.course FROM Teacher_courses tc WHERE tc.teacher.id = :teacherId", Course.class);
            query.setParameter("teacherId", teacherId);
            return query.getResultList();
        }
    }
}