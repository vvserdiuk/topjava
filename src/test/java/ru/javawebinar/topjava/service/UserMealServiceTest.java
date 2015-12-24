package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.DbPopulator;

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
        UserMeal userMeal = service.get(1, 100000);
        MATCHER.assertEquals(MealTestData.LUNCH_USER, userMeal);
    }
    @Test
    public void testGetLunchAdmin() throws Exception {
        UserMeal userMeal = service.get(3, 100001);
        MATCHER.assertEquals(MealTestData.LUNCH_ADM, userMeal);
    }

    @Test
    public void testGetDinnerUser() throws Exception {
        UserMeal userMeal = service.get(2, 100000);
        MATCHER.assertEquals(MealTestData.DINNER_USER, userMeal);
    }
    @Test
    public void testGetDinnerAdm() throws Exception {
        UserMeal userMeal = service.get(4, 100001);
        MATCHER.assertEquals(MealTestData.DINNER_ADM, userMeal);
    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testGetBetweenDates() throws Exception {

    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {

    }

    @Test
    public void testGetAll() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testSave() throws Exception {

    }
}