package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class UserMealServiceImpl implements UserMealService {

    protected final LoggerWrapper LOG = LoggerWrapper.get(getClass());

    @Autowired
    private UserMealRepository repository;

    @Override
    public UserMeal create(UserMeal userMeal, int userId) {
        LOG.info("in service create " + userMeal + " userId:" + userId);
        UserMeal userMealTmp = repository.save(userMeal, userId);
        LOG.info(userMealTmp.toString());
        ExceptionUtil.check(userMealTmp, userId);
        return userMealTmp;
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        ExceptionUtil.check(repository.delete(id, userId), id);

    }

    @Override
    public UserMeal get(int id, int userId) {
        UserMeal userMeal = repository.get(id, userId);
        ExceptionUtil.check(userMeal, id);
        return userMeal;
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        List<UserMeal> userMeals = repository.getAll(userId);
        ExceptionUtil.check(userMeals, userId);
        return userMeals;
    }

    @Override
    public void update(UserMeal userMeal, int userId) {
        ExceptionUtil.check(repository.save(userMeal, userId), userMeal.getId());
    }


    @Override
    public List<UserMeal> getFiltered(LocalDate fromDate, LocalDate toDate, LocalTime fromTime, LocalTime toTime, int userId) {
        //TODO
        return null;
    }

    @Override
    public List<UserMeal> getFilteredByDate(LocalDate fromDate, LocalDate toDate, int usetId) {
        List<UserMeal> result = new ArrayList<>();
        List<UserMeal> userMeals = repository.getAll(usetId);
        for (UserMeal usermeal: userMeals ) {
            if (TimeUtil.isBetweenDate(usermeal.getDateTime().toLocalDate(), fromDate, toDate)){
                result.add(usermeal);
            }
        }
        return result;
    }

    @Override
    public List<UserMeal> getFilteredByTime(LocalTime fromTime, LocalTime toTime, int userId) {
        //TODO
        return null;
    }
}
