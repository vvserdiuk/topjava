package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.List;

/**
 * Created by vvserdiuk on 05.12.2015.
 */
public interface UserMealDao {

    public void add(UserMeal userMeal);

    public UserMeal get(int id);

    public List<UserMeal> selectAll();

    public void edit(int id, UserMeal userMeal);

    public void delete(int userId);
}
