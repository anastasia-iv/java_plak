package ru.msu.cmc.scheduler.DAO;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.scheduler.models.Teacher;

import java.util.ArrayList;
import java.util.List;

//import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class TeacherDAOTest {

    @Autowired
    private TeacherDAO teacherDAO;

//    common
    @Test
//    @Transactional
    void testUpdateTeacher() {
        Teacher teacher = teacherDAO.getById(3);
        teacher.setCathedra("ИБ");
        teacherDAO.update(teacher);
        Teacher updatedTeacher = teacherDAO.getById(3);
        assertEquals("ИБ", updatedTeacher.getCathedra());
    }


    @Test
    @Transactional
    void testSimpleManipulations() {
        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(new Teacher(16, "Александрова Анна Витальевна", "msu_alexandrova", "ИИТ","Преподаватель"));
        teacherList.add(new Teacher(17, "Максимова Елизавета Яковлевна", "mmp_maksimova", "ММП", "Профессор"));
        teacherDAO.saveCollection(teacherList);
        List<Teacher> teacherListAll = (List<Teacher>) teacherDAO.getAll();
        assertEquals(17, teacherListAll.size());

        List<Teacher> frn = teacherDAO.getAllTeachersByName("Максимова Елизавета Яковлевна");
        assertEquals(1, frn.size());
        assertEquals("Максимова Елизавета Яковлевна", frn.get(0).getName());

        Teacher frn_one = teacherDAO.getSingleTeacherByName("Максимова Елизавета Яковлевна");
        assertEquals("Максимова Елизавета Яковлевна", frn_one.getName());

        Teacher teacherNotExist = teacherDAO.getById(100);
        assertNull(teacherNotExist);

        Teacher stepanova = teacherDAO.getById(3);
        assertEquals("Степанова София Викторовна", stepanova.getName());
    }

    @Test
    void testGetTeachersByCathedra() {
        Teacher teacher1 = new Teacher(18, "Чечеткина Евгения Павловна", "cheche_1506", "ВТМ", "Кандидат математических наук");
        teacherDAO.save(teacher1);
        List<Teacher> filteredTeachers = teacherDAO.getTeachersByCathedra("ВТМ");
        assertEquals(1, filteredTeachers.size());
        assertEquals("Чечеткина Евгения Павловна", filteredTeachers.get(0).getName());
    }

}