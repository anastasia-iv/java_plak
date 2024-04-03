package ru.msu.cmc.scheduler.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import java.util.Objects;

@Entity
@Table(name = "auditorium")
public class Auditorium implements CommonEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "auditorium_id")
    private int id;

    @Column(nullable = false, name = "auditorium_name")
    private String name;

    @Column(name = "capacity")
    private int capacity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auditorium other = (Auditorium) o;
        return Objects.equals(id, other.id)
                && name.equals(other.name)
                && Objects.equals(capacity, other.capacity)
    }
}
