package ru.msu.cmc.scheduler.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import java.util.Objects;

@Entity
@Table(name = "teachers")
public class Teacher implements CommonEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "teacher_id")
    private int id;

    @Column(nullable = false, name = "teacher_name")
    private String name;

    @Column(nullable = false, name = "mail")
    private String mail;

    @Column(name = "cathedra")
    private String cathedra;

    @Column(name = "academic_title")
    private String academic_title;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher other = (Teacher) o;
        return Objects.equals(id, other.id)
                && name.equals(other.name)
                && mail.equals(other.mail)
                && cathedra.equals(other.cathedra)
                && academic_title.equals(other.academic_title)
    }
}
