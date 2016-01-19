package ru.javawebinar.topjava.web.meal;

import org.junit.Test;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * Created by vvserdiuk on 17.01.2016.
 */
public class UserMealRestControllerTest extends AbstractControllerTest {

    public static final String REST_URL = UserMealRestController.REST_URL + '/';

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + MEAL1_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(MEAL1));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + MEAL1_ID))
                .andDo(print())
                .andExpect(status().isOk());
        List<UserMeal> expected = new ArrayList<>(USER_MEALS);
        expected.remove(MEAL1);
        MATCHER.assertCollectionEquals(expected, mealService.getAll(LoggedUser.id()));
    }

    @Test
    public void testGetAll() throws Exception {
        List<UserMealWithExceed> expected = UserMealsUtil.getWithExceeded(USER_MEALS, LoggedUser.getCaloriesPerDay());
        mockMvc.perform(get(REST_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER_WITH_EXCEEDED.contentListMatcher(expected));
    }

    @Test
    public void testUpdate() throws Exception {
        UserMeal expected = MEAL1;
        expected.setDescription("другой завтрак");
        mockMvc.perform(put(REST_URL + MEAL1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected)))
                .andDo(print())
                .andExpect(status().isOk());

        MATCHER.assertEquals(expected, mealService.get(MEAL1_ID, LoggedUser.id()));
    }

    @Test
    public void testCreate() throws Exception {
        UserMeal expected = new UserMeal(LocalDateTime.of(2015, Month.MAY, 29, 10, 0), "Завтрак", 500);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());

        UserMeal returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());

        MATCHER.assertEquals(expected, returned);
        List<UserMeal> expectedCollection = new ArrayList<>(USER_MEALS);
        expectedCollection.add(expected);
        MATCHER.assertCollectionEquals(expectedCollection, mealService.getAll(LoggedUser.id()));

    }

    @Test
    public void testGetBetween() throws Exception {
        List<UserMealWithExceed> expected =
                UserMealsUtil.getFilteredWithExceeded(USER_MEALS, LocalTime.of(10, 0), LocalTime.of(14, 0), LoggedUser.getCaloriesPerDay());

        DateTimeFormatter isoDate = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate startDate = LocalDate.parse("2015-05-30", isoDate);
        mockMvc.perform(get(REST_URL + "filter?startDateTime=2015-05-30T10:00&endDateTime=2015-05-31T14:00")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(startDate)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER_WITH_EXCEEDED.contentListMatcher(expected));
    }
}