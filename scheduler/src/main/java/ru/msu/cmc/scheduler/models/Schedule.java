package ru.msu.cmc.scheduler.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Objects;

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

    @Column(nullable = false, name = "weekday")
    private String weekday;

    @Column(nullable = false, name = "time")
    private Time time;

    @Column(nullable = false, name = "sh_group")
    private Integer sh_group;

    @Column(name = "year")
    private Integer year;

    public Schedule(
            Integer id,
            Teacher_courses tc_id,
            Auditorium auditorium_id,
            String weekday,
            Time time,
            Integer sh_group,
            Integer year
    ) {
        this.id = id;
        this.tc_id = tc_id;
        this.auditorium_id = auditorium_id;
        this.weekday = weekday;
        this.time = time;
        this.sh_group = sh_group;
        this.year = year;
    }

    public Schedule() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule other = (Schedule) o;
        return Objects.equals(id, other.id)
                && Objects.equals(tc_id, other.tc_id)
                && Objects.equals(auditorium_id, other.auditorium_id)
                && Objects.equals(weekday, other.weekday)
                && Objects.equals(time, other.time)
                && Objects.equals(sh_group, other.sh_group)
                && Objects.equals(year, other.year);
    }
}
