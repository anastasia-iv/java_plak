package ru.msu.cmc.scheduler.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "teachers")
@Getter
@Setter
public class Teacher implements CommonEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "teacher_id")
    private Integer id;

    @Column(nullable = false, name = "teacher_name")
    private String name;

    @Column(nullable = false, name = "mail")
    private String mail;

    @Column(name = "cathedra")
    private String cathedra;

    @Column(name = "academic_title")
    private String academic_title;

    public Teacher(
            Integer id,
            String name,
            String mail,
            String cathedra,
            String academic_title
    ) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.cathedra = cathedra;
        this.academic_title = academic_title;
    }

    public Teacher() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher other = (Teacher) o;
        return Objects.equals(id, other.id)
                && name.equals(other.name)
                && mail.equals(other.mail)
                && cathedra.equals(other.cathedra)
                && academic_title.equals(other.academic_title);
    }
}
