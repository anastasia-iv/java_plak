package ru.msu.cmc.scheduler.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "courses")
@Getter
@Setter
public class Course implements CommonEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "course_id")
    private Integer id;

    @Column(nullable = false, name = "course_name")
    private String name;

    @Column(nullable = false, name = "course_type")
    private String type;

    @Column(nullable = false, name = "year")
    private Integer year;

    @Column(nullable = false, name = "flow")
    private Integer flow;

    @Column(name = "extra_course")
    private String extra_course;

    public Course(
            Integer id,
            String name,
            String type,
            Integer year,
            Integer flow,
            String extra_course
    ) {
            this.id = id;
            this.name = name;
            this.type = type;
            this.year = year;
            this.flow = flow;
            this.extra_course = extra_course;
    }
    public Course() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course other = (Course) o;
        return Objects.equals(id, other.id)
                && name.equals(other.name)
                && type.equals(other.type)
                && Objects.equals(year, other.year)
                && Objects.equals(flow, other.flow)
                && extra_course.equals(other.extra_course);
    }
}
