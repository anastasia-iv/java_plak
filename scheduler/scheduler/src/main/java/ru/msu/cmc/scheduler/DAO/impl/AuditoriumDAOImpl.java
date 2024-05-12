package ru.msu.cmc.scheduler.DAO.impl;
import ru.msu.cmc.scheduler.models.*;
import ru.msu.cmc.scheduler.utils.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.scheduler.DAO.AuditoriumDAO;
import ru.msu.cmc.scheduler.models.Auditorium;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import jakarta.persistence.TypedQuery;

import java.util.List;

@Repository
public class AuditoriumDAOImpl extends CommonDAOImpl<Auditorium, Integer> implements AuditoriumDAO {

    public AuditoriumDAOImpl() {
        super();
        setEntityClass(Auditorium.class);
    }
}