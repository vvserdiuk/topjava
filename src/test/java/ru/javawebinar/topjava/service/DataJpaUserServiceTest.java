package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;

import java.util.List;

import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL_ID;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;

/**
 * Created by vvserdiuk on 03.01.2016.
 */
@ActiveProfiles({Profiles.HSQLDB, Profiles.DATAJPA})
public class DataJpaUserServiceTest extends UserServiceTest {

    @Autowired
    UserMealService userMealService;
    @Test
    public void testGetWithUser() throws Exception {
        User actual = userMealService.getWithUser(ADMIN_MEAL_ID, ADMIN_ID).getUser();
        UserTestData.MATCHER.assertEquals(UserTestData.ADMIN, actual);
    }

    @Test
    public void testGetWithMeal() throws Exception{
        List<UserMeal> userMeals = service.getWithMeal(UserTestData.USER_ID).getMeals();
        MealTestData.MATCHER.assertCollectionEquals(userMeals, MealTestData.USER_MEALS);
    }
}
