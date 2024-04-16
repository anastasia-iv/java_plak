package ru.msu.cmc.scheduler.utils;

import ru.msu.cmc.scheduler.models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil(){}

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null) {
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().build();
            try {
                sessionFactory = new MetadataSources(registry)
                        .addAnnotatedClasses(Auditorium.class,
                                Course.class,
                                Schedule.class,
                                Student_courses.class,
                                Teacher.class,
                                Teacher_courses.class)
                        .buildMetadata()
                        .buildSessionFactory();
            }
            catch (Exception e) {
                // StandardServiceRegistryBuilder.destroy(registry);
                // e.printStackTrace();
                throw e;
            }
        }
        return sessionFactory;
    }
}