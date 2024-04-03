package ru.msu.cmc.sheduler.models;

public interface CommonEntity<ID> {
    ID getId();
    void setId(ID id);
}