package ru.msu.cmc.scheduler.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "teacher_courses")
@Getter
@Setter
public class Teacher_courses implements CommonEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "tc_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacher_id")
    @NonNull
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    @NonNull
    private Course course;

    public Teacher_courses(
            Integer id,
            Teacher teacher,
            Course course
    ) {
        this.id = id;
        this.teacher = teacher;
        this.course = course;
    }

    public Teacher_courses() {

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher_courses other = (Teacher_courses) o;
        return Objects.equals(id, other.id)
                && teacher.equals(other.teacher)
                && course.equals(other.course);
    }
}
