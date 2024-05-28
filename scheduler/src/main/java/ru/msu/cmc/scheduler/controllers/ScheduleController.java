package ru.msu.cmc.scheduler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.msu.cmc.scheduler.DAO.ScheduleDAO;
import ru.msu.cmc.scheduler.DAO.impl.*;
import ru.msu.cmc.scheduler.models.*;
import ru.msu.cmc.scheduler.DAO.TeacherDAO;
import ru.msu.cmc.scheduler.DAO.AuditoriumDAO;
import ru.msu.cmc.scheduler.DAO.CourseDAO;
import ru.msu.cmc.scheduler.DAO.Teacher_coursesDAO;


import java.sql.Time;
import java.util.List;

@Controller
public class ScheduleController {

    @Autowired
    private final ScheduleDAO scheduleDAO = new ScheduleDAOImpl();

    @GetMapping("/index")
    public String schedulelistPage(Model model) {
        List<Schedule> schedules = (List<Schedule>) scheduleDAO.getAll();
        model.addAttribute("schedules", schedules);
        model.addAttribute("scheduleService", scheduleDAO);
        return "index";
    }

    @GetMapping("/currentSchedule")
    public String getCurrentSchedule(Model model) {
        List<Schedule> schedules = scheduleDAO.getCurrentSchedule();
        model.addAttribute("schedules", schedules);
        return "index"; // assuming your main page template is named 'main.html'
    }


    @GetMapping("/schedule")
    public String schedulePage(@RequestParam(name = "scheduleId") Integer scheduleId, Model model) {
        Schedule schedule = scheduleDAO.getById(scheduleId);

        if (schedule == null) {
            model.addAttribute("error_msg", "В базе нет ячейки расписания с ID = " + scheduleId);
            return "error";
        }

        model.addAttribute("schedule", schedule);
        model.addAttribute("scheduleService", scheduleDAO);
        return "schedule";
    }

    @GetMapping("/editSchedule")
    public String editSchedulePage(@RequestParam(name = "scheduleId", required = false) Integer scheduleId, Model model) {
        if (scheduleId == null) {
            model.addAttribute("schedule", new Schedule());
            model.addAttribute("scheduleService", scheduleDAO);
            return "editSchedule";
        }

        Schedule schedule = scheduleDAO.getById(scheduleId);

        if (schedule == null) {
            model.addAttribute("error_msg", "В базе нет ячейки расписания с ID = " + scheduleId);
            return "error";
        }

        model.addAttribute("schedule", schedule);
        model.addAttribute("scheduleService", scheduleDAO);
        return "editSchedule";
    }

    @GetMapping("/filteredSchedule")
    public String getFilteredSchedule(
            @RequestParam(value = "weekday", required = false) String weekday,
            @RequestParam(value = "sh_group", required = false) Integer sh_group,
            @RequestParam(value = "year", required = false) Integer year,
            Model model) {

        List<Schedule> schedules = scheduleDAO.getScheduleByFilter(weekday, sh_group, year);
        model.addAttribute("schedules", schedules);
        return "index"; // assuming your main page template is named 'main.html'
    }

    @PostMapping("/saveSchedule")
    public String saveSchedulePage(@RequestParam(name = "cellId", required = false) Integer cellId,
                                   @RequestParam(name = "weekday") String weekday,
                                   @RequestParam(name = "time") String time,
                                   @RequestParam(name = "teacherId") Integer teacherId,
                                   @RequestParam(name = "courseId") Integer courseId,
                                   @RequestParam(name = "auditoriumId") Integer auditoriumId,
                                   @RequestParam(name = "group") Integer group,
                                   Model model) {

        Schedule schedule;
        if (cellId != null) {
            schedule = scheduleDAO.getById(cellId);
            if (schedule == null) {
                model.addAttribute("error_msg", "В базе нет расписания с ID = " + cellId);
                return "error";
            }
        } else {
            schedule = new Schedule();
        }
        Teacher_coursesDAO teacherCoursesDAO = new Teacher_coursesDAOImpl();
        TeacherDAO teacherDAO = new TeacherDAOImpl();
        CourseDAO courseDAO = new CourseDAOImpl();
        AuditoriumDAO auditoriumDAO = new AuditoriumDAOImpl();

        Teacher teacher = teacherDAO.getById(teacherId);
        Course course = courseDAO.getById(courseId);
        Auditorium auditorium = auditoriumDAO.getById(auditoriumId);

        if (teacher == null || course == null || auditorium == null) {
            model.addAttribute("error_msg", "Неверные ID преподавателя, курса или аудитории");
            return "error";
        }

        Teacher_courses teacherCourse = teacherCoursesDAO.findByTeacherAndCourse(teacher, course);
        if (teacherCourse == null) {
            teacherCourse = new Teacher_courses(null, teacher, course);
            teacherCoursesDAO.save(teacherCourse);
        }

        schedule.setWeekday(weekday);
        schedule.setTime(Time.valueOf(time));
        schedule.setTc_id(teacherCourse);
        schedule.setAuditorium_id(auditorium);
        schedule.setSh_group(group);

        scheduleDAO.update(schedule);

        return "redirect:/index";
    }


//    @PostMapping("/removeSchedule")
//    public String removeSchedulePage(@RequestParam(name = "scheduleId") Long scheduleId) {
//        scheduleDAO.deleteById(scheduleId);
//        return "redirect:/schedules";
//    }
}