package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.TimeUtil;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * User: gkislin
 * Date: 26.08.2014
 */

@Repository
public class JdbcUserMealRepositoryImpl implements UserMealRepository {

    private static final LoggerWrapper LOG = LoggerWrapper.get(JdbcUserMealRepositoryImpl.class);

    private static final RowMapper<UserMeal> ROW_MAPPER = new RowMapper<UserMeal>() {
        @Override
        public UserMeal mapRow(ResultSet rs, int rowNum) throws SQLException {
            Integer id = rs.getInt("id");
            LocalDateTime dateTime = rs.getTimestamp("datetime").toLocalDateTime();
            String description = rs.getString("description");
            Integer calories = rs.getInt("calories");

            return new UserMeal(id, dateTime, description, calories);
        }
    };

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertUserMeal;

    @Autowired
    public JdbcUserMealRepositoryImpl(DataSource dataSource) {
        this.insertUserMeal = new SimpleJdbcInsert(dataSource)
                .withTableName("MEALS")
                .usingGeneratedKeyColumns("id");
    }
    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        Integer mealId = userMeal.getId();

        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", mealId)
                .addValue("datetime", Timestamp.valueOf(userMeal.getDateTime()))
                .addValue("description", userMeal.getDescription())
                .addValue("calories", userMeal.getCalories())
                .addValue("user_id", userId);

        if (userMeal.isNew()){
            LOG.info("before executeAndReturnKey()");
            Number newKey = insertUserMeal.executeAndReturnKey(map);

            LOG.info("after executeAndReturnKey()");
            userMeal.setId(newKey.intValue());
        }
        else if (get(mealId, userId)==null) {
            return null;
        }
        else {
//            namedParameterJdbcTemplate.update(
//                    "UPDATE meals SET id=:id, datetime=:datetime, descriprion=:description, " +
//                    "calories=:calories, user_id=:user_id", map);
            jdbcTemplate.update(
                    "UPDATE meals SET datetime=?, description=?, calories=?, user_id=? WHERE id=?",
                    Timestamp.valueOf(userMeal.getDateTime()), userMeal.getDescription(), userMeal.getCalories(), userId, mealId
            );

        }
        return userMeal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM meals WHERE id=? AND user_id=?", id, userId) != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        List<UserMeal> meals = jdbcTemplate.query(
                "SELECT * FROM meals WHERE id=? AND user_id=?", ROW_MAPPER, id, userId);
        return DataAccessUtils.singleResult(meals);
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return jdbcTemplate.query("SELECT * FROM meals WHERE user_id=? ORDER BY datetime DESC ", ROW_MAPPER, userId);
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        Timestamp start = Timestamp.valueOf(startDate);
        Timestamp end   = Timestamp.valueOf(endDate);
        return jdbcTemplate.query(
                "SELECT * FROM meals WHERE user_id=? AND datetime>? AND datetime<? ORDER BY datetime DESC ",
                ROW_MAPPER, userId, start, end
        );
    }
}
