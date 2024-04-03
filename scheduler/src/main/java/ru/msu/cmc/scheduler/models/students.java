package ru.msu.cmc.scheduler.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import java.util.Objects;

@Entity
@Table(name = "students")
public class Student implements CommonEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "student_id")
    private int id;

    @Column(nullable = false, name = "student_name")
    private String name;

    @Column(nullable = false, name = "st_flow")
    private int flow;

    @Column(name = "st_group")
    private int group;

    @Column(name = "year")
    private int year;

    @Column(name = "average")
    private String average;

    @Column(name = "portfolio")
    private String portfolio;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student other = (Student) o;
        return Objects.equals(id, other.id)
                && name.equals(other.name)
                && Objects.equals(flow, other.flow)
                && Objects.equals(group, other.group)
                && average.equals(other.average)
                && year.equals(other.year)
                && portfolio.equals(other.portfolio)
    }
}
