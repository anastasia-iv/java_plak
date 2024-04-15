package ru.msu.cmc.scheduler.DAO;

import lombok.Builder;
import lombok.Getter;
import ru.msu.cmc.scheduler.models.Teacher;

import java.util.List;

public interface TeacherDAO extends CommonDAO<Teacher, Long> {

    List<Teacher> getAllTeacherByName(String TeacherName);

    Teacher getSingleTeacherByName(String TeacherName);

    List<Teacher> getTeachersByCathedra(String Cathedra);
}sss