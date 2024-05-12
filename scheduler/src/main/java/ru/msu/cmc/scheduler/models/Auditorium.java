package ru.msu.cmc.scheduler.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "auditorium")
@Getter
@Setter
public class Auditorium implements CommonEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "auditorium_id")
    private Integer id;

    @Column(nullable = false, name = "auditorium_name")
    private String name;

    @Column(name = "capacity")
    private Integer capacity;

    public Auditorium(
            Integer id,
            String name,
            Integer capacity
    ) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    public Auditorium() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auditorium other = (Auditorium) o;
        return Objects.equals(id, other.id)
                && name.equals(other.name)
                && Objects.equals(capacity, other.capacity);
    }
}
