package ru.javawebinar.topjava.web.meal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by vvserdiuk on 26.01.2016.
 */
@RestController
@RequestMapping("/ajax/meals")
public class AjaxUserMealController extends AbstractUserMealController{

    @Override
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserMealWithExceed> getAll() {
        return super.getAll();
    }

    @Override
    @RequestMapping(value = "/filter", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserMealWithExceed> getBetween(
            @RequestParam(value = "startDate", required = false) LocalDate startDate,
            @RequestParam(value = "startTime", required = false) LocalTime startTime,
            @RequestParam(value = "endDate", required = false)   LocalDate endDate,
            @RequestParam(value = "endTime", required = false)   LocalTime endTime) {
         return super.getBetween(
                startDate != null ? startDate : TimeUtil.MIN_DATE, startTime != null ? startTime : LocalTime.MIN,
                endDate != null ? endDate : TimeUtil.MAX_DATE, endTime != null ? endTime : LocalTime.MAX);
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createOrUpdate(@RequestParam("id") int id,
                               @RequestParam("dateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime,
                               @RequestParam("description") String description,
                               @RequestParam("calories") int calories) {

        UserMeal userMeal = new UserMeal(id, dateTime, description, calories);
        if (id == 0) {
            super.create(userMeal);
        } else {
            super.update(userMeal, id);
        }
    }

}
