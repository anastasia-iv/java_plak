package ru.msu.cmc.scheduler.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.msu.cmc.scheduler.DAO.CourseDAO;
import ru.msu.cmc.scheduler.DAO.impl.CourseDAOImpl;
import ru.msu.cmc.scheduler.models.Course;

import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private final CourseDAO courseDAO = new CourseDAOImpl();

    @GetMapping("/courses")
    public String courselistPage(Model model) {
        List<Course> courses = (List<Course>) courseDAO.getAll();
        model.addAttribute("courses", courses);
        model.addAttribute("courseService", courseDAO);
        return "courses";
    }

    @GetMapping("/course")
    public String coursePage(@RequestParam(name = "courseId") Integer courseId, Model model) {
        Course course = courseDAO.getById(courseId);

        if (course == null) {
            model.addAttribute("error_msg", "В базе нет курса с ID = " + courseId);
            return "error";
        }

        model.addAttribute("course", course);
        model.addAttribute("courseService", courseDAO);
        return "course";
    }

    @GetMapping("/editCourse")
    public String editCoursePage(@RequestParam(name = "courseId", required = false) Integer courseId, Model model) {
        if (courseId == null) {
            model.addAttribute("course", new Course());
            model.addAttribute("courseService", courseDAO);
            return "editCourse";
        }

        Course course = courseDAO.getById(courseId);

        if (course == null) {
            model.addAttribute("error_msg", "В базе нет человека с ID = " + courseId);
            return "error";
        }

        model.addAttribute("course", course);
        model.addAttribute("courseService", courseDAO);
        return "editCourse";
    }

    @PostMapping("/saveCourse")
    public String saveCoursePage(@RequestParam(name = "courseId") Integer courseId,
                                  @RequestParam(name = "name") String name,
                                  @RequestParam(name = "type") String type,
                                  @RequestParam(name = "year") Integer year,
                                  @RequestParam(name = "flow") Integer flow,
                                  @RequestParam(name = "extra_course") String extra_course,
                                  Model model) {
        Logger logger = LoggerFactory.getLogger(HomeController.class);

        logger.info("Saving student with id: {}, name: {}, type: {}, year: {}, flow: {}, extra_courses: {}",
                courseId, name, type, year, flow, extra_course);

        Course course = courseDAO.getById(courseId);
        boolean changeIsSuccessful = false;

        if (course != null) {
            course.setName(name);
            course.setType(type);
            course.setYear(year);
            course.setFlow(flow);
            course.setExtra_course(extra_course);
            courseDAO.update(course);
        } else {
            course = new Course(courseId, name, type, year, flow, extra_course);
            courseDAO.save(course);
        }

        model.addAttribute("error_msg", "Данные не сохранены");
        return "redirect:/course?courseId=" + course.getId();
    }

//    @PostMapping("/removeCourse")
//    public String removeCoursePage(@RequestParam(name = "courseId") Long courseId) {
//        courseDAO.deleteById(courseId);
//        return "redirect:/courses";
//    }
}