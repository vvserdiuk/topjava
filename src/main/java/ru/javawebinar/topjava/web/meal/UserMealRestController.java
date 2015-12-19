package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.service.UserMealServiceImpl;
import ru.javawebinar.topjava.service.UserServiceImpl;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class UserMealRestController {

    protected final LoggerWrapper LOG = LoggerWrapper.get(getClass());

    @Autowired
    private UserMealServiceImpl service;

    @Autowired
    private UserServiceImpl userService;

    public List<UserMealWithExceed> getAll() {
        LOG.info("getAll");
        return UserMealsUtil.getWithExceeded(service.getAll(LoggedUser.id()), UserMealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public UserMeal get(int id) {
        LOG.info("get " + id);
        return service.get(id, LoggedUser.id());
    }

    public List<UserMealWithExceed> getFiltered(LocalDate fromDate, LocalDate toDate, LocalTime startTime, LocalTime endTime){
        LOG.info("getFiltered");
        List<UserMeal> filteredByDate = service.getFilteredByDate(fromDate, toDate, LoggedUser.id());
        return UserMealsUtil
                .getFilteredWithExceeded(filteredByDate, startTime, endTime, userService.get(LoggedUser.id()).getCaloriesPerDay());
    }
    public UserMeal create(UserMeal userMeal) {
        LOG.info("create " + userMeal);
        return service.create(userMeal, LoggedUser.id());
    }

    public void delete(int id) {
        LOG.info("delete " + id);
        service.delete(id, LoggedUser.id());
    }

    public void update(UserMeal userMeal, int id) {
        userMeal.setId(id);
        LOG.info("update " + userMeal);
        service.update(userMeal, LoggedUser.id());
    }
}
