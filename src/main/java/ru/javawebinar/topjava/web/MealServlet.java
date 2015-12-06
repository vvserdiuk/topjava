package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.dao.UserMealDao;
import ru.javawebinar.topjava.dao.UserMealDaoImpl;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vvserdiuk on 04.12.2015.
 */
public class MealServlet extends HttpServlet {
    private static final LoggerWrapper LOG = LoggerWrapper.get(MealServlet.class);

    UserMealDao userMealDao = new UserMealDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserMealWithExceed> userMealWithExceedList = UserMealsUtil
                .getFilteredMealsWithExceeded(userMealDao.selectAll(), LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);

        String action = req.getParameter("action");
        System.out.println(req.getAttributeNames().hasMoreElements());
        if (action == null){
            LOG.debug("action=null");
            req.setAttribute("meals", userMealWithExceedList);
            req.getRequestDispatcher("/mealList.jsp").forward(req, resp);
        }
        else if (action.equals("delete")){
            LOG.debug("action=delete");
            userMealDao.delete(Integer.parseInt(req.getParameter("id")));
            resp.sendRedirect("meals");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String id = req.getParameter("id");

        if (action.equals("add")){
            LOG.debug("action=add");

            LocalDateTime localDateTime = LocalDateTime.parse(req.getParameter("datetime"));
            UserMeal userMeal = new UserMeal(localDateTime, req.getParameter("description"),
                    Integer.parseInt(req.getParameter("calories")));

            if(id == null) {
                LOG.debug("add userMeal");
                userMealDao.add(userMeal);
            }
            else {
                LOG.debug("edit userMeal");
                userMealDao.edit(Integer.parseInt(id), userMeal);
            }
            resp.sendRedirect("meals");
        }
        else if (action.equals("edit")){
            LOG.debug("action=edit");
            LOG.debug("idEdit=" + Integer.parseInt(req.getParameter("id")));
            req.setAttribute("meal", userMealDao.get(Integer.parseInt(req.getParameter("id"))));
            req.getRequestDispatcher("/editPage.jsp").forward(req, resp);
        }
    }
}

