package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.time.Month;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);

    public static final UserMeal LUNCH_USER   =
            new UserMeal(1, LocalDateTime.of(2015, Month.NOVEMBER, 30, 14, 00), "обед", 1000);
    public static final UserMeal DINNER_USER =
            new UserMeal(2, LocalDateTime.of(2015, Month.NOVEMBER, 30, 18, 00), "ужин", 500);

    public static final UserMeal LUNCH_USER_FOR_SAVING   =
            new UserMeal(5, LocalDateTime.of(2015, Month.NOVEMBER, 30, 14, 00), "обед", 1000);

    public static final UserMeal LUNCH_ADM =
            new UserMeal(3, LocalDateTime.of(2015, Month.NOVEMBER, 30, 14, 00), "обед Админ", 1100);
    public static final UserMeal DINNER_ADM =
            new UserMeal(4, LocalDateTime.of(2015, Month.NOVEMBER, 30, 18, 00), "ужин Админ", 600);
}
