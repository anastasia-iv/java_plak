package ru.msu.cmc.scheduler.utils;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HibernateSessionFactoryUtil {
    @Getter
    static SessionFactory sessionFactory;


    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        HibernateSessionFactoryUtil.sessionFactory = sessionFactory;
    }


}
