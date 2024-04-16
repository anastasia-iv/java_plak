package ru.msu.cmc.scheduler.DAO;

import ru.msu.cmc.scheduler.models.Student;

import java.util.List;

public interface StudentDAO extends CommonDAO<Student, Integer> {

    List<Student> getAllStudentsByName(String studentName);

    Student getSingleStudentByName(String studentName);

    List<Student> getStudentByFilter(int stGroup, int stFlow, int year);
}