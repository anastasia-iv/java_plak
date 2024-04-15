package ru.msu.cmc.scheduler.DAO;

import lombok.Builder;
import lombok.Getter;
import ru.msu.cmc.scheduler.models.Student;

import java.util.List;

public interface StudentDAO extends CommonDAO<Student, Long> {

    List<Student> getAllStudentsByName(String studentName);

    Student getSingleStudentByName(String studentName);

    List<Student> getStudentByFilter(int stGroup, int stFlow, int year);
}