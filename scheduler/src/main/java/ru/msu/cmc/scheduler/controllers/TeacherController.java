package ru.msu.cmc.scheduler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.msu.cmc.scheduler.DAO.TeacherDAO;
import ru.msu.cmc.scheduler.DAO.impl.TeacherDAOImpl;
import ru.msu.cmc.scheduler.models.Teacher;

import java.util.List;

@Controller
public class TeacherController {

    @Autowired
    private final TeacherDAO teacherDAO = new TeacherDAOImpl();

    @GetMapping("/teachers")
    public String teacherlistPage(Model model) {
        List<Teacher> teachers = (List<Teacher>) teacherDAO.getAll();
        model.addAttribute("teachers", teachers);
        model.addAttribute("teacherService", teacherDAO);
        return "teachers";
    }

    @GetMapping("/teacher")
    public String teacherPage(@RequestParam(name = "teacherId") Integer teacherId, Model model) {
        Teacher teacher = teacherDAO.getById(teacherId);

        if (teacher == null) {
            model.addAttribute("error_msg", "В базе нет преподавателя с ID = " + teacherId);
            return "error";
        }

        model.addAttribute("teacher", teacher);
        model.addAttribute("teacherService", teacherDAO);
        return "teacher";
    }

    @GetMapping("/editTeacher")
    public String editTeacherPage(@RequestParam(name = "teacherId", required = false) Integer teacherId, Model model) {
        if (teacherId == null) {
            model.addAttribute("teacher", new Teacher());
            model.addAttribute("teacherService", teacherDAO);
            return "editTeacher";
        }

        Teacher teacher = teacherDAO.getById(teacherId);

        if (teacher == null) {
            model.addAttribute("error_msg", "В базе нет человека с ID = " + teacherId);
            return "error";
        }

        model.addAttribute("teacher", teacher);
        model.addAttribute("teacherService", teacherDAO);
        return "editTeacher";
    }

    @PostMapping("/saveTeacher")
    public String saveTeacherPage(@RequestParam(name = "teacherId") Integer teacherId,
                                 @RequestParam(name = "name") String name,
                                 @RequestParam(name = "mail") String mail,
                                 @RequestParam(name = "cathedra", required = false) String cathedra,
                                 @RequestParam(name = "academic_title", required = false) String academic_title,
                                 Model model) {
        Teacher teacher = teacherDAO.getById(teacherId);
        boolean changeIsSuccessful = false;

        if (teacher != null) {
            teacher.setName(name);
            teacher.setMail(mail);
            teacher.setCathedra(cathedra);
            teacher.setAcademic_title(academic_title);
        } else {
            teacher = new Teacher(teacherId, name, mail, cathedra, academic_title);
        }

        model.addAttribute("error_msg", "Данные не сохранены");
        return "error";
    }

//    @PostMapping("/removeTeacher")
//    public String removeTeacherPage(@RequestParam(name = "teacherId") Long teacherId) {
//        teacherDAO.deleteById(teacherId);
//        return "redirect:/teachers";
//    }
}