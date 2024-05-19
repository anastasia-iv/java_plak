package ru.msu.cmc.scheduler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.msu.cmc.scheduler.DAO.ScheduleDAO;
import ru.msu.cmc.scheduler.DAO.impl.ScheduleDAOImpl;
import ru.msu.cmc.scheduler.models.Auditorium;
import ru.msu.cmc.scheduler.models.Schedule;
import ru.msu.cmc.scheduler.models.Teacher_courses;

import java.sql.Time;
import java.util.List;

@Controller
public class ScheduleController {

    @Autowired
    private final ScheduleDAO scheduleDAO = new ScheduleDAOImpl();

    @GetMapping("/general")
    public String schedulelistPage(Model model) {
        List<Schedule> schedules = (List<Schedule>) scheduleDAO.getAll();
        model.addAttribute("schedules", schedules);
        model.addAttribute("scheduleService", scheduleDAO);
        return "general";
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

    @PostMapping("/saveSchedule")
    public String saveSchedulePage(@RequestParam(name = "scheduleId") Integer scheduleId,
                                   @RequestParam(name = "tc_id") Teacher_courses tc_id,
                                   @RequestParam(name = "auditorium_id") Auditorium auditorium_id,
                                 @RequestParam(name = "weekday") String weekday,
                                 @RequestParam(name = "time") Time time,
                                 @RequestParam(name = "sh_group", required = false) Integer sh_group,
                                 @RequestParam(name = "year", required = false) Integer year,
                                 Model model) {
        Schedule schedule = scheduleDAO.getById(scheduleId);
        boolean changeIsSuccessful = false;

        if (schedule != null) {
            schedule.setTc_id(tc_id);
            schedule.setAuditorium_id(auditorium_id);
            schedule.setWeekday(weekday);
            schedule.setSh_group(sh_group);
            schedule.setYear(year);
        } else {
            schedule = new Schedule(scheduleId, tc_id, auditorium_id, weekday, time, sh_group, year);
        }

        model.addAttribute("error_msg", "Данные не сохранены");
        return "error";
    }

//    @PostMapping("/removeSchedule")
//    public String removeSchedulePage(@RequestParam(name = "scheduleId") Long scheduleId) {
//        scheduleDAO.deleteById(scheduleId);
//        return "redirect:/schedules";
//    }
}