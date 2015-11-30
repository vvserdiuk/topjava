package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

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

        Map<Integer, Integer> dayToCalories = new HashMap<>();
        List<UserMealWithExceed> result = new ArrayList<>();

        for (UserMeal meal: mealList) {
            Integer currentDay = meal.getDateTime().getDayOfYear();
            if (dayToCalories.containsKey(currentDay)){
                Integer newValue = dayToCalories.get(currentDay) + meal.getCalories();
                dayToCalories.replace(currentDay, newValue);
            }
            else {
                dayToCalories.put(currentDay, meal.getCalories());
            }
        }

        for (UserMeal meal: mealList){
            if (TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime)) {
                Integer day = meal.getDateTime().getDayOfYear();
                result.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(),
                        dayToCalories.get(day) > caloriesPerDay));
            }
        }



//        mealList.stream().map(meal -> dayToCalories.containsKey(meal.getDateTime().getDayOfYear())
//                ? dayToCalories.replace(meal.getDateTime().getDayOfYear(), dayToCalories.get(meal.getDateTime().getDayOfYear()) + meal.getCalories())
//                : dayToCalories.put(meal.getDateTime().getDayOfYear(), meal.getCalories())).count();
//
//        mealList.stream()
//                .filter(meal -> meal.getDateTime().toLocalTime().isAfter(startTime)
//                && meal.getDateTime().toLocalTime().isBefore(endTime))
//                .forEach(s -> result.add( new UserMealWithExceed(s.getDateTime(), s.getDescription(), s.getCalories(),
//                        dayToCalories.get(s.getDateTime().getDayOfYear()) > caloriesPerDay)) );
//
//

        return result;
    }
}
