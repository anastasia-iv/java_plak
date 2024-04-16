package ru.msu.cmc.scheduler.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "schedule")
@Getter
@Setter
public class Schedule implements CommonEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "cell_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tc_id", referencedColumnName = "tc_id")
    private Teacher_courses tc_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "auditorium_id", referencedColumnName = "auditorium_id")
    private Auditorium auditorium_id;

    @Column(nullable = false, name = "time")
    private Time time;

    @Column(name = "st_group")
    private Integer group;

    @Column(name = "year")
    private Integer year;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule other = (Schedule) o;
        return Objects.equals(id, other.id)
                && Objects.equals(tc_id, other.tc_id)
                && Objects.equals(auditorium_id, other.auditorium_id)
                && Objects.equals(time, other.time)
                && Objects.equals(group, other.group)
                && Objects.equals(year, other.year);
    }
}
