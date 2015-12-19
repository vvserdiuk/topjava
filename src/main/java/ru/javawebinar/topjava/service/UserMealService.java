package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */
public interface UserMealService {

    UserMeal create(UserMeal userMeal, int userId);

    void delete(int id, int userId) throws NotFoundException;

    UserMeal get(int id, int userId) throws NotFoundException;

    List<UserMeal> getAll(int userId);

    void update(UserMeal userMeal, int userId);

    List<UserMeal> getFiltered(LocalDate fromDate, LocalDate toDate, LocalTime fromTime, LocalTime toTime, int userId);

    List<UserMeal> getFilteredByDate(LocalDate fromDate, LocalDate toDate, int userId);

    List<UserMeal> getFilteredByTime(LocalTime fromTime, LocalTime toTime, int userId);
}
