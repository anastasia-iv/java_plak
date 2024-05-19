package ru.msu.cmc.scheduler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.msu.cmc.scheduler.DAO.StudentDAO;
import ru.msu.cmc.scheduler.DAO.impl.StudentDAOImpl;
import ru.msu.cmc.scheduler.models.Student;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private final StudentDAO studentDAO = new StudentDAOImpl();

    @GetMapping("/students")
    public String studentlistPage(Model model) {
        List<Student> students = (List<Student>) studentDAO.getAll();
        model.addAttribute("students", students);
        model.addAttribute("studentService", studentDAO);
        return "students";
    }

    @GetMapping("/student")
    public String studentPage(@RequestParam(name = "studentId") Integer studentId, Model model) {
        Student student = studentDAO.getById(studentId);

        if (student == null) {
            model.addAttribute("error_msg", "В базе нет студента с ID = " + studentId);
            return "error";
        }

        model.addAttribute("student", student);
        model.addAttribute("studentService", studentDAO);
        return "student";
    }

    @GetMapping("/editStudent")
    public String editStudentPage(@RequestParam(name = "studentId", required = false) Integer studentId, Model model) {
        if (studentId == null) {
            model.addAttribute("student", new Student());
            model.addAttribute("studentService", studentDAO);
            return "editStudent";
        }

        Student student = studentDAO.getById(studentId);

        if (student == null) {
            model.addAttribute("error_msg", "В базе нет студента с ID = " + studentId);
            return "error";
        }

        model.addAttribute("student", student);
        model.addAttribute("studentService", studentDAO);
        return "editStudent";
    }

    @PostMapping("/saveStudent")
    public String saveStudentPage(@RequestParam(name = "studentId") Integer studentId,
                                 @RequestParam(name = "name") String name,
                                 @RequestParam(name = "flow") Integer flow,
                                 @RequestParam(name = "group") Integer group,
                                 @RequestParam(name = "year") Integer year,
                                 @RequestParam(name = "average") Float average,
                                 @RequestParam(name = "portfolio", required = false) String portfolio,
                                 Model model) {
        Student student = studentDAO.getById(studentId);
        boolean changeIsSuccessful = false;

        if (student != null) {
            student.setName(name);
            student.setFlow(flow);
            student.setGroup(group);
            student.setYear(year);
            student.setAverage(average);
            student.setPortfolio(portfolio);
        } else {
            student = new Student(studentId, name, flow, group, year, average, portfolio);
        }

        model.addAttribute("error_msg", "Данные не сохранены");
        return "error";
    }

//    @PostMapping("/removeStudent")
//    public String removeStudentPage(@RequestParam(name = "studentId") Long studentId) {
//        studentDAO.deleteById(studentId);
//        return "redirect:/students";
//    }
}