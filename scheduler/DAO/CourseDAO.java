package ru.msu.cmc.scheduler.DAO;

import lombok.Builder;
import lombok.Getter;
import ru.msu.cmc.scheduler.models.Course;

import java.util.List;

public interface CourseDAO extends CommonDAO<Course, Long> {

    List<Course> getAllCoursesByName(String CourseName);

    Course getSingleCourseByName(String CourseName);

    List<Course> getExtraCourses();
}