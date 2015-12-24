package ru.javawebinar.topjava.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;


/**
 * Created by vvserdiuk on 23.12.2015.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMealServiceTest {

    @Autowired
    protected UserMealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testGetLunchUser() throws Exception {
        UserMeal userMeal = service.get(1, 100_000);
        MATCHER.assertEquals(MealTestData.LUNCH_USER, userMeal);
    }
    @Test
    public void testGetLunchAdmin() throws Exception {
        UserMeal userMeal = service.get(3, 100_001);
        MATCHER.assertEquals(MealTestData.LUNCH_ADM, userMeal);
    }

    @Test
    public void testGetDinnerUser() throws Exception {
        UserMeal userMeal = service.get(2, 100_000);
        MATCHER.assertEquals(MealTestData.DINNER_USER, userMeal);
    }
    @Test
    public void testGetDinnerAdm() throws Exception {
        UserMeal userMeal = service.get(4, 100_001);
        MATCHER.assertEquals(MealTestData.DINNER_ADM, userMeal);
    }
    @Test(expected = NotFoundException.class)
    public void testGetSmbElseFood() throws Exception {
        service.get(4, 100_000);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(1, 100_000);
        Collection<UserMeal> expected = new ArrayList<>();
        expected.add(MealTestData.DINNER_USER);
        MATCHER.assertCollectionEquals(expected, service.getAll(100_000));
    }


    @Test
    public void testUpdate() throws Exception {
        UserMeal userMeal = MealTestData.LUNCH_USER;
        userMeal.setCalories(userMeal.getCalories()-100);
        MATCHER.assertEquals(userMeal, service.update(userMeal, 100_000));
    }

    @Test
    public void testSave() throws Exception {
        UserMeal userMeal = service.save(MealTestData.LUNCH_USER_FOR_SAVING, 100_000);
        MATCHER.assertEquals(MealTestData.LUNCH_USER_FOR_SAVING, userMeal);
        Assert.assertEquals(new Integer(5), userMeal.getId());
    }
}