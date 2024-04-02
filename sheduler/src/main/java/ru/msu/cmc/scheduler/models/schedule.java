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
    @Column(nullable = false, name = "cell_id")
    private int id;

    @Column(nullable = false, name = "tc_id")
    private int tc_id;

    @Column(name = "st_group")
    private int group;

    @Column(name = "auditorium_id")
    private int auditorium_id;

    @Column(nullable = false, name = "weekday")
    private String weekday;

    @Column(nullable = false, name = "time")
    private Time time;

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
