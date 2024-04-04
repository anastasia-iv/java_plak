package ru.msu.cmc.scheduler.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import java.util.Objects;

@Entity
@Table(name = "teacher_courses")
public class Teacher_courses implements CommonEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "tc_id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacher_id")
    @NonNull
    private Student students;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    @NonNull
    private Course courses;
}
