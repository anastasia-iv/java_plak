package ru.msu.cmc.scheduler.DAO.impl;

import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.scheduler.DAO.ScheduleDAO;
import ru.msu.cmc.scheduler.models.Schedule;
import ru.msu.cmc.scheduler.utils.HibernateSessionFactoryUtil;
import java.time.LocalDateTime;
import java.time.DayOfWeek;

import java.util.Arrays;
import java.util.List;

@Repository
public class ScheduleDAOImpl extends CommonDAOImpl<Schedule, Integer> implements ScheduleDAO {

    public ScheduleDAOImpl() {
        super();
        setEntityClass(Schedule.class);
    }

//    public LocalDateTime getCurrentTime() {
//        return LocalDateTime.now();
//    }

//    @Override
//    public List<Schedule> getAllSchedulesByName(String ScheduleName) {
//        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
//            TypedQuery<Schedule> query = session.createQuery("SELECT st FROM Schedule st WHERE st.name = :ScheduleName", Schedule.class);
//            query.setParameter("ScheduleName", ScheduleName);
//            return query.getResultList().isEmpty() ? null : query.getResultList();
//        }
//    }

    @Override
    public List<Schedule> getCurrentSchedule() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();){
            LocalDateTime current_time = LocalDateTime.now() ;
            DayOfWeek dayOfWeek = current_time.getDayOfWeek();
            List<String> array = Arrays.asList("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat");
            int dayOfWeekValue = dayOfWeek.getValue();
            String cur_weekday = array.get(dayOfWeekValue);
            TypedQuery<Schedule> query = session.createQuery("SELECT sch FROM Schedule sch WHERE sch.weekday = :cur_weekday", Schedule.class);
            query.setParameter("cur_weekday", cur_weekday);
            return query.getResultList();
        }
    }

    @Override
    @Transactional
    public List<Schedule> getScheduleByFilter(String weekday, int sh_group, int year) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
            TypedQuery<Schedule> query = session.createQuery("SELECT st FROM Schedule st WHERE st.weekday = :weekday AND st.sh_group = :sh_group AND st.year = :year", Schedule.class);
            query.setParameter("weekday", weekday);
            query.setParameter("sh_group", sh_group);
            query.setParameter("year", year);
            return query.getResultList();
        }
    }

}