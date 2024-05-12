package ru.msu.cmc.scheduler.DAO;

import ru.msu.cmc.scheduler.models.Teacher;

import java.util.List;

public interface TeacherDAO extends CommonDAO<Teacher, Integer> {

    List<Teacher> getAllTeachersByName(String TeacherName);

    Teacher getSingleTeacherByName(String TeacherName);

    List<Teacher> getTeachersByCathedra(String Cathedra);
}