package ru.msu.cmc.scheduler.controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.msu.cmc.scheduler.DAO.StudentDAO;
import ru.msu.cmc.scheduler.DAO.Teacher_coursesDAO;
import ru.msu.cmc.scheduler.DAO.TeacherDAO;
import ru.msu.cmc.scheduler.DAO.impl.StudentDAOImpl;
import ru.msu.cmc.scheduler.DAO.impl.TeacherDAOImpl;
import ru.msu.cmc.scheduler.DAO.impl.Teacher_coursesDAOImpl;
import ru.msu.cmc.scheduler.models.Teacher_courses;
import ru.msu.cmc.scheduler.models.Course;

import java.util.List;

@Controller
public class Teacher_coursesController {
    @Autowired
    private final Teacher_coursesDAO teacher_coursesDAO = new Teacher_coursesDAOImpl();
    @Autowired
    private final TeacherDAO teacherDAO = new TeacherDAOImpl();

    @GetMapping("/teacherСourses")
    public String getCoursesByTeacherName(@RequestParam("teacherId") Integer teacherId, Model model) {
        List<Course> courses = teacher_coursesDAO.findCoursesByTeacherId(teacherId);
        model.addAttribute("courses", courses);
        model.addAttribute("teacher", teacherDAO.getById(teacherId));
        return "teacher_courses";
    }
}
