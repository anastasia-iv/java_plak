package ru.msu.cmc.scheduler.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import java.util.Objects;

@Entity
@Table(name = "schedule")
public class Schedule implements CommonEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "cell_id")
    private int id;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tc_id", referencedColumnName = "id")
    @NonNull
    private Teacher_courses teacher_course;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "auditorium_id", referencedColumnName = "id")
    @NonNull
    private Auditorium auditorium;

    @Column(nullable = false, name = "time")
    private Time time;

    @Column(name = "st_group")
    private int group;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule other = (Schedule) o;
        return Objects.equals(id, other.id)
                && Objects.equals(teacher_course, other.teacher_course)
                && Objects.equals(auditorium, other.auditorium)
                && Objects.equals(time, other.time)
                && Objects.equals(group, other.group)
                && Objects.equals(year, other.year)
    }
}
