package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vvserdiuk on 05.12.2015.
 */
public class UserMealDaoImpl implements UserMealDao {

    private Map<Integer, UserMeal> dbMock = new HashMap<>();


    {
        add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    @Override
    public void add(UserMeal userMeal) {
        dbMock.put(userMeal.getId(), userMeal);
    }

    @Override
    public UserMeal get(int id) {
        return dbMock.get(id);
    }

    @Override
    public List<UserMeal> selectAll() {
        List<UserMeal> userMealList = new ArrayList<>();
        dbMock.forEach((id, um) -> userMealList.add(um));
        return userMealList;
    }

    @Override
    public void edit(int id, UserMeal userMeal) {
        dbMock.put(id, userMeal);
    }

    @Override
    public void delete(int userId) {
        dbMock.remove(userId);
    }
}
