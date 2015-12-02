package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );

        for (UserMealWithExceed meal :getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000)) {
            System.out.println(meal.toString());
        }
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> dayToCalories = new HashMap<>();
        List<UserMealWithExceed> result = new ArrayList<>();

        for (UserMeal meal: mealList) {

            LocalDate currentDay = meal.getDateTime().toLocalDate();
            // My decision
            if (dayToCalories.containsKey(currentDay)){
                Integer newValue = dayToCalories.get(currentDay) + meal.getCalories();
                dayToCalories.put(currentDay, newValue);
            }
            else {
                dayToCalories.put(currentDay, meal.getCalories());
            }

            //Java 8 decision
//            dayToCalories.put(currentDay, dayToCalories.getOrDefault(currentDay, 0) + meal.getCalories());

        }

        for (UserMeal meal: mealList){
            LocalDateTime dateTime = meal.getDateTime();
            if (TimeUtil.isBetween(dateTime.toLocalTime(), startTime, endTime)) {
                result.add(new UserMealWithExceed(dateTime, meal.getDescription(), meal.getCalories(),
                        dayToCalories.get(dateTime.toLocalDate()) > caloriesPerDay));
            }
        }
        return result;
    }
}
