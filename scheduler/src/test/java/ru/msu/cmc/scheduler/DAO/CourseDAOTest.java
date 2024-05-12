package ru.msu.cmc.scheduler.DAO;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.scheduler.models.Course;

import java.util.ArrayList;
import java.util.List;

//import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class CourseDAOTest {

    @Autowired
    private CourseDAO courseDAO;

    //    common
    @Test
//    @Transactional
    void testUpdateCourse() {
        Course course = courseDAO.getById(8);
        course.setType("seminar");
        courseDAO.update(course);
        Course updatedCourse = courseDAO.getById(8);
        assertEquals("seminar", updatedCourse.getType());
    }


    @Test
    @Transactional
    void testSimpleManipulations() {
        List<Course> courseList = new ArrayList<>();
        courseList.add(new Course(12, "Функц. анализ", "lecture", 3, 3, null));
        courseList.add(new Course(13, "Введение в сети", "lecture", 3, 3, null));
        courseDAO.saveCollection(courseList);
        List<Course> courseListAll = (List<Course>) courseDAO.getAll();
        assertEquals(14, courseListAll.size());

        List<Course> frn = courseDAO.getAllCoursesByName("Ассемблер");
        assertEquals(1, frn.size());
        assertEquals("Ассемблер", frn.get(0).getName());

        Course frn_one = courseDAO.getSingleCourseByName("Ассемблер");
        assertEquals("Ассемблер", frn_one.getName());

        Course courseNotExist = courseDAO.getById(100);
        assertNull(courseNotExist);

        Course algo = courseDAO.getById(4);
        assertEquals("Алгоритмы", algo.getName());
    }

    @Test
    void testGetExtraCourses() {
        Course course1 = new Course(14, "Задачи функц. анализа", "lecture", 3, 3, "extra");
        courseDAO.save(course1);
        List<Course> filteredCourses = courseDAO.getExtraCourses();
        assertEquals(2, filteredCourses.size());
        assertEquals("Задачи функц. анализа", filteredCourses.get(0).getName());
    }

}