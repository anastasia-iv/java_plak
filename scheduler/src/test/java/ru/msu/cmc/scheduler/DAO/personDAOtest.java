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
        List<Person> studentList = new ArrayList<>();
        studentList.add(new Student(1, "Федорова Анна Дмитриевна", 1, null, 1245L, 3, 5.00, "Иванниковские чтения"));
        studentList.add(new Student(null, "Латыпова Аделина Ильнуровна", 2, 327, null, 5.00, "Победитель олимпиады Физтех по математике"));
        studentList.add(new Student("Мирошкина Павла Александровна", 3, "Всесибирская олимпиада по математике"));
        studentDAO.saveCollection(studentList);
    }
}