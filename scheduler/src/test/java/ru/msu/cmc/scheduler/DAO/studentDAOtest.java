package ru.msu.cmc.scheduler.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.scheduler.StudentDAO;
import ru.msu.cmc.scheduler.model.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class StudentDAOTest {

    @Autowired
    private StudentDAO studentDAO;
    @Autowired
    private SessionFactory sessionFactory;

//    common
    @Test
    void testUpdateStudent() {
        Student student = StudentDAO.getById(3);
        student.setflow(2);
        studentDAO.update(student);
        Student updatedStudent = StudentDAO.getById(3);
        assertEquals(2, updatedStudent.getflow());
    }

    @Test
    void testSimpleManipulations() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(46, "Федорова Анна Дмитриевна", 1, null, 1245L, 3, 5.00, "Иванниковские чтения"));
        studentList.add(new Student(47, "Латыпова Аделина Ильнуровна", 2, 327, null, 5.00, "Победитель олимпиады Физтех по математике"));
        studentList.add(new Student(48, "Мирошкина Павла Александровна", 3, "Всесибирская олимпиада по математике"));
        studentDAO.saveCollection(studentList);

        List<Student> studentListAll = studentDAO.getAll();
        assertEquals(48, studentListAll.size());

        List<Student> frn = studentDAO.getAllStudentByName("Федорова Анна Дмитриевна");
        assertEquals(1, frn.size());
        assertEquals("Федорова Анна Дмитриевна", frn.get(0).getName());

        Student studentId47 = studentDAO.getById(47L);
        assertEquals(47, studentId47.getId());

        Student studentNotExist = studentDAO.getById(50L);
        assertNull(studentNotExist);

        Student student = StudentDAO.getById(47);
        studentDAO.delete(student);
        assertNull(studentDAO.getById(47));
    }

    @Test
    void testGetByFilter() {
        StudentDAOImpl studentDAO = new StudentDAOImpl();
        Student student1 = new Student(47, "Дударенко Денис Вадимович", 3, 326, 3, 4.56, "");
        studentDAO.save(student1);
        List<Student> filteredStudents = studentDAO.getStudentByFilter(326, 3, 3);
        assertEquals(1, filteredStudents.size());
        assertEquals(student1, filteredStudents.get(0));
    }

}