package ru.msu.cmc.scheduler.DAO;

import ru.msu.cmc.scheduler.models.CommonEntity;

import java.util.Collection;

public interface CommonDAO<T extends CommonEntity<ID>, ID> {
    T getById(ID id);

    Collection<T> getAll();

    void save(T entity);

    void saveCollection(Collection<T> entities);

    void deleteById(ID id);

    void update(T entity);
}