package ru.msu.cmc.scheduler.DAO.impl;

import org.springframework.stereotype.Repository;
import ru.msu.cmc.scheduler.DAO.AuditoriumDAO;
import ru.msu.cmc.scheduler.models.Auditorium;

@Repository
public class AuditoriumDAOImpl extends CommonDAOImpl<Auditorium, Integer> implements AuditoriumDAO {

    public AuditoriumDAOImpl() {
        super();
        setEntityClass(Auditorium.class);
    }


}