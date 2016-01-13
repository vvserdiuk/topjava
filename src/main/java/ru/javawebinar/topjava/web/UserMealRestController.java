package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.web.meal.AbstractUserMealRestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


/**
 * Created by vvserdiuk on 11.01.2016.
 */
@Controller
@RequestMapping("/meals")
public class UserMealRestController extends AbstractUserMealRestController {

    @Autowired
    private UserMealService service;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model){
        model.addAttribute("mealList", super.getAll());
        return "mealList";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("id") Integer id){
        super.delete(id);
        return "redirect:/meals";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(@RequestParam("id") Integer id,  Model model){
        model.addAttribute("meal", super.get(id));
        return "mealEdit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@RequestParam("id") Integer id, Model model, HttpServletRequest request){
        UserMeal userMeal = new UserMeal(
                id,
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));
        super.update(userMeal);
        return "redirect:/meals";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model){
        UserMeal userMeal = new UserMeal(LocalDateTime.now(), "", 0);
        model.addAttribute("meal", userMeal);
        return "mealEdit";
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public String filter(Model model, HttpServletRequest request){
        LocalDate startDate = TimeUtil.parseLocalDate(request.getParameter("startDate"), TimeUtil.MIN_DATE);
        LocalDate endDate = TimeUtil.parseLocalDate(request.getParameter("endDate"), TimeUtil.MAX_DATE);
        LocalTime startTime = TimeUtil.parseLocalTime(request.getParameter("startTime"), LocalTime.MIN);
        LocalTime endTime = TimeUtil.parseLocalTime(request.getParameter("endTime"), LocalTime.MAX);
        List<UserMealWithExceed> mealList = super.getBetween(startDate, startTime, endDate, endTime);
        model.addAttribute("mealList", mealList);
        return "mealList";
    }


}