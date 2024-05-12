package ru.msu.cmc.scheduler.DAO;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.scheduler.models.Student;

import java.util.ArrayList;
import java.util.List;

//import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class StudentDAOTest {

    @Autowired
    private StudentDAO studentDAO;

//    common
    @Test
//    @Transactional
    void testUpdateStudent() {
        Student student = studentDAO.getById(3);
        student.setFlow(2);
        studentDAO.update(student);
        Student updatedStudent = studentDAO.getById(3);
        assertEquals(2, updatedStudent.getFlow());
    }


    @Test
    @Transactional
    void testSimpleManipulations() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(46, "Федорова Анна Дмитриевна", 1, 327, 2003, 5.00F, "Иванниковские чтения"));
        studentList.add(new Student(47, "Латыпова Аделина Ильнуровна", 2, 327, null, 5.00F, "Победитель олимпиады Физтех по математике"));
        studentDAO.saveCollection(studentList);

        List<Student> studentListAll = (List<Student>) studentDAO.getAll();
        assertEquals(48, studentListAll.size());

        List<Student> frn = studentDAO.getAllStudentsByName("Федорова Анна Дмитриевна");
        assertEquals(1, frn.size());
        assertEquals("Федорова Анна Дмитриевна", frn.get(0).getName());

        Student frn_one = studentDAO.getSingleStudentByName("Федорова Анна Дмитриевна");
        assertEquals("Федорова Анна Дмитриевна", frn_one.getName());

        Student studentNotExist = studentDAO.getById(100);
        assertNull(studentNotExist);

        Student platon = studentDAO.getById(11);
        assertEquals("Беспалов Платон Никитович", platon.getName());

    }

    @Test
    void testGetByFilter() {
        Student student1 = new Student(47, "Дударенко Денис Вадимович", 3, 326, 3, 4.56F, "");
        studentDAO.save(student1);
        List<Student> filteredStudents = studentDAO.getStudentByFilter(326, 3, 3);
        assertEquals(1, filteredStudents.size());
        assertEquals("Дударенко Денис Вадимович", filteredStudents.get(0).getName());
    }

}