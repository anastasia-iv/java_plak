package ru.msu.cmc.scheduler.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Student implements CommonEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "student_id")
    private Integer id;

    @Column(nullable = false, name = "student_name")
    private String name;

    @Column(nullable = false, name = "st_flow")
    private Integer flow;

    @Column(name = "st_group")
    private Integer group;

    @Column(name = "year")
    private Integer year;

    @Column(name = "average")
    private Float average;

    @Column(name = "portfolio")
    private String portfolio;

    public Student(
            Integer id,
            String name,
            Integer flow,
            Integer group,
            Integer year,
            Float average,
            String portfolio
    ) {
        this.id = id;
        this.name = name;
        this.flow = flow;
        this.group = group;
        this.year = year;
        this.average = average;
        this.portfolio = portfolio;
    }

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
                && Objects.equals(flow, other.year)
                && portfolio.equals(other.portfolio);
    }
}
