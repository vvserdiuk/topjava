package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;

import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL;
import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL_ID;
import static ru.javawebinar.topjava.MealTestData.MATCHER;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;

/**
 * Created by vvserdiuk on 03.01.2016.
 */
@ActiveProfiles({Profiles.HSQLDB, Profiles.DATAJPA})
public class DataJpaUserMealServiceTestDataJpa extends UserMealServiceTest {

    @Test
    public void testGetWithUser() throws Exception {
        User actual = service.getWithUser(ADMIN_MEAL_ID, ADMIN_ID).getUser();
        UserTestData.MATCHER.assertEquals(UserTestData.ADMIN, actual);
    }
}
