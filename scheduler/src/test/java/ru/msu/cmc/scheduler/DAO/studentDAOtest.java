package ru.msu.cmc.scheduler.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.scheduler.models.Student;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class StudentDAOTest {

    @Autowired
    private StudentDAO studentDAO;
    @Autowired
    private SessionFactory sessionFactory;

    @BeforeEach
    void beforeEach() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1, "Федорова Анна Дмитриевна", 1, null, 1245L, 3, 5.00, "Иванниковские чтения"));
        studentList.add(new Student(null, "Латыпова Аделина Ильнуровна", 2, 327, null, 5.00, "Победитель олимпиады Физтех по математике"));
        studentList.add(new Student("Мирошкина Павла Александровна", 3, "Всесибирская олимпиада по математике"));
        studentDAO.saveCollection(studentList);
    }

    @Test
    void testSimpleManipulations() {
        List<Student> studentListAll = studentDAO.getAll();
        assertEquals(45, studentListAll.size());

        List<Student> frn = studentDAO.getAllStudentByName("Федоров Роман Николаевич");
        assertEquals(1, frn.size());
        assertEquals("Федоров Роман Николаевич", frn.get(0).getName());

        Student studentId3 = studentDAO.getById(5L);
        assertEquals(5, studentId3.getId());

        Student studentNotExist = studentDAO.getById(50L);
        assertNull(studentNotExist);
    }

    @Test
    void testGetByFilter() {
        // Предположим, у нас есть DAO и некоторые тестовые данные
        StudentDAOImpl studentDAO = new StudentDAOImpl();

        // Создаем несколько студентов с разными группами, потоками и годами
        Student student1 = new Student(46, "Соколова Арина Олеговна", 3, 328, 3, 4.82, "");
        Student student2 = new Student(47, "Дударенко Денис Вадимович", 3, 327, 3, 4.56, "");
        Student student3 = new Student(48, "Гикало Егор Дмитриевич", 3, 328, 3, 4.64, "");
        Student student4 = new Student(49, "Балабанов Федор Михайлович", 3, 328, 3, 4.44, "");
        Student student5 = new Student(50, "Панов Даниил Семёнович", 3, 327, 3, 4.7, "");

        // Сохраняем студентов в базе данных
        studentDAO.save(student1);
        studentDAO.save(student2);
        studentDAO.save(student3);
        studentDAO.save(student4);
        studentDAO.save(student5);

        // Фильтруем студентов по группе 1, потоку 2 и году 2021
        List<Student> filteredStudents = studentDAO.getByFilter(327, 3, 3);

        // Проверяем, что в списке содержатся только студенты, которые соответствуют заданным параметрам
        assertEquals(1, filteredStudents.size());
        assertEquals(student3, filteredStudents.get(0));
    }
}

}