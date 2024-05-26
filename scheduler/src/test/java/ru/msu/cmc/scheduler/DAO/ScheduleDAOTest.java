package ru.msu.cmc.scheduler.DAO;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.scheduler.models.Auditorium;
import ru.msu.cmc.scheduler.models.Schedule;
import ru.msu.cmc.scheduler.models.Teacher_courses;

import java.sql.Time;

import java.util.ArrayList;
import java.util.List;

//import static org.junit.Assert.assertNotNull;
import static java.sql.JDBCType.TIME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class ScheduleDAOTest {

    @Autowired
    private ScheduleDAO scheduleDAO;

    @Autowired
    private Teacher_coursesDAO teacher_coursesDAO;

    @Autowired
    private AuditoriumDAO auditoriumDAO;

    //    common
    @Test
//    @Transactional
    void testUpdateSchedule() {
        Schedule schedule = scheduleDAO.getById(15);
        schedule.setWeekday("Tue");
        scheduleDAO.update(schedule);
        Schedule updatedSchedule = scheduleDAO.getById(15);
        assertEquals("Tue", updatedSchedule.getWeekday());
    }


    @Test
    @Transactional
    void testSimpleManipulations() {
        List<Schedule> scheduleList = new ArrayList<>();
        Teacher_courses tc1 = teacher_coursesDAO.getById(7);
        Auditorium auditorium1 = auditoriumDAO.getById(7);
        scheduleList.add(new Schedule(29, tc1, auditorium1, "Sun",  Time.valueOf("10:30:00"), 327, 3));
        scheduleList.add(new Schedule(30, tc1, auditorium1, "Sat", Time.valueOf("08:45:00"), 327, 3));
        scheduleDAO.saveCollection(scheduleList);

        // Проверка, что данные были сохранены
        Schedule savedSchedule1 = scheduleDAO.getById(29);
        Schedule savedSchedule2 = scheduleDAO.getById(30);
        assertEquals("Sun", savedSchedule1.getWeekday());
        assertEquals("Sat", savedSchedule2.getWeekday());


        List<Schedule> scheduleListAll = (List<Schedule>) scheduleDAO.getAll();
        assertEquals(16, scheduleListAll.size());

    }

    @Test
    void testGetByFilter() {
        Teacher_courses tc2 = teacher_coursesDAO.getById(2);
        Auditorium auditorium2 = auditoriumDAO.getById(2);
        Schedule schedule2 = new Schedule(31, tc2, auditorium2, "Sun", Time.valueOf("08:45:00"), 601, 6);
        scheduleDAO.save(schedule2);
        List<Schedule> filteredSchedules = scheduleDAO.getScheduleByFilter("Sun", 601, 6);
        assertEquals(1, filteredSchedules.size());
    }
}