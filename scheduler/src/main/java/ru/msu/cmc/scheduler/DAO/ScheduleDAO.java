package ru.msu.cmc.scheduler.DAO;

import ru.msu.cmc.scheduler.models.Schedule;

import java.util.List;

public interface ScheduleDAO extends CommonDAO<Schedule, Integer> {

    List<Schedule> getCurrentSchedule();

    List<Schedule> getScheduleByFilter(int weekday, int sh_group, int year);

}