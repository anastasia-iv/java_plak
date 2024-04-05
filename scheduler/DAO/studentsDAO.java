package ru.msu.cmc.scheduler.DAO;

import lombok.Builder;
import lombok.Getter;
import ru.msu.cmc.scheduler.models.Student;

import java.util.List;

public interface StudentDAO extends CommonDAO<Student, Long> {

    List<Student> getAllStudentsByName(String studentName);
    Student getSingleStudentByName(String studentName);
    List<Student> getByFilter(Filter filter);

    @Builder
    @Getter
    class Filter {
        private String name;
    }

    static Filter.FilterBuilder getFilterBuilder() {
        return Filter.builder();
    }
}